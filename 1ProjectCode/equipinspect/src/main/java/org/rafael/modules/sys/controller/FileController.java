package org.rafael.modules.sys.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.rafael.modules.alg.entity.AlgEquip;
import org.rafael.modules.alg.entity.AlgTaskDetail;
import org.rafael.modules.alg.service.AlgEquipService;
import org.rafael.modules.alg.service.AlgTaskDetailImgService;
import org.rafael.modules.alg.service.AlgTaskDetailService;
import org.rafael.modules.util.mvcbase.BaseController;
import org.rafael.util.json.JsonMessage;
import org.rafael.util.json.JsonMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;

@Controller
@RequestMapping(value = "/file/")
public class FileController extends BaseController {
	@Autowired
	private AlgTaskDetailImgService algTaskDetailImgService;
	@Autowired
	private AlgTaskDetailService algTaskDetailService;
	@Autowired
	private AlgEquipService algEquipService;

	@RequestMapping(value = "uploadview")
	public String uploadView() {
		return "modules/sys/uploadView";
	}
	
	@RequestMapping(value = "updatemanifest")
	@ResponseBody
	public JsonMessage updatemanifest(HttpServletRequest request) {
		String file = request.getSession().getServletContext()
				.getRealPath("")
				+ "static/manifest/rafael.manifest";
		modifyFileContent(file,"#VERSION","#VERSION "+System.currentTimeMillis());
		return JsonMessageUtil.successMsg();
	}
	
	/**
     * 修改文件内容
     * @param fileName
     * @param oldstr
     * @param newStr
     * @return
     */
    public static boolean modifyFileContent(String fileName, String oldstr, String newStr) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fileName, "rw");
            String line = null;
            long lastPoint = 0; //记住上一次的偏移量
            while ((line = raf.readLine()) != null) {
                final long ponit = raf.getFilePointer();
                if(line.contains(oldstr)){
                      String str=newStr;
                raf.seek(lastPoint);
                raf.writeBytes(str);
                }
                lastPoint = ponit; 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

	/**
	 * 上传处理方法-不支持分块传输chunk
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public JsonMessage upload(HttpServletRequest request,
			HttpServletResponse response) {
		String uploadPath = request.getSession().getServletContext()
				.getRealPath("")
				+ "/upload";
		String filename = "";
		try {
			// Apache文件上传组件判断contentType是否是multipart/开头，也就是是否是上传表单的数据
			if (ServletFileUpload.isMultipartContent(request)) {
				// 使用Apache文件上传组件处理文件上传步骤：
				// 1、创建一个DiskFileItemFactory工厂
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// /设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。如果不指定，那么缓冲区的大小默认是10KB
				factory.setSizeThreshold(1024);
				/*
				 * factory.setRepository(new File(repositoryPath));//
				 * 设置上传时生成的临时文件的保存目录
				 */
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 解决上传文件名的中文乱码
				upload.setHeaderEncoding("UTF-8");
				// 监听文件上传进度
				upload.setProgressListener(new ProgressListener() {
					public void update(long pBytesRead, long pContentLength,
							int arg2) {
						System.out.println("文件大小为：" + pContentLength
								+ ",当前已处理：" + pBytesRead);
					}
				});
				// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
				// upload.setFileSizeMax(1024*1024*10);
				// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB 超过这个大小上传会不成功
				// upload.setSizeMax(1024*1024*30);

				// 使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
				List<FileItem> items = upload.parseRequest(request);
				String equipid = "";
				for (FileItem item : items) {
					// 如果fileitem中封装的是普通输入项的数据
					if (item.isFormField()) /* 是文本域 */
					{
						String name = item.getFieldName();
						if (name.equals("equipid")) {
							equipid = item.getString("UTF-8");
						}
						// 解决普通输入项的数据的中文乱码问题
						// String value = item.getString("UTF-8");
						// value = new
						// String(value.getBytes("iso8859-1"),"UTF-8");
						// System.out.println(name + "=" + value);
					} else { /* 如果是文件类型 */
						// 得到上传的文件名称，
						filename = item.getName();
						// System.out.println("上传的文件名称: "+filename);
						if (filename == null || filename.trim().equals("")) {
							continue;
						}
						// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
						// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						filename = filename.substring(filename
								.lastIndexOf("\\") + 1);
						// 得到上传文件的扩展名
						String fileExtName = filename.substring(filename
								.lastIndexOf(".") + 1);
						// filename =
						// filename.substring(0,filename.lastIndexOf("."));
						// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
						// System.out.println("上传的文件的扩展名是："+fileExtName);
						// 保存文件
						// 文件名+uuid
						String id = UUID.randomUUID().toString();
						filename = id + "-" + filename;
						File savedFile = new File(uploadPath, filename);
						item.write(savedFile);

						Thumbnails.of(savedFile).size(60, 60)
								.toFile(uploadPath + "/thumb/" + filename);

						uploadedInsertEquip(id, equipid, filename);
					}
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			return JsonMessageUtil.customFailureMsg("单个文件超出最大值！！！");
		} catch (FileUploadBase.SizeLimitExceededException e) {
			e.printStackTrace();
			return JsonMessageUtil.customFailureMsg("上传文件的总的大小超出限制的最大值！！！");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonMessageUtil.customFailureMsg("其他异常，上传失败！！！");
		}
		JsonMessage jsonMessage = JsonMessageUtil.successMsg();
		jsonMessage.setExtend(filename);

		return jsonMessage;

	}

	private void uploadedInsertEquip(String id, String equipid,
			String fileName) {
		AlgEquip record = new AlgEquip();
		record.setId(equipid);
		record.setImage(fileName);
		algEquipService.updateByPrimaryKeySelective(record );
	}

	public static boolean GenerateImage(String imgStr, String imgFilePath) {
		// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
