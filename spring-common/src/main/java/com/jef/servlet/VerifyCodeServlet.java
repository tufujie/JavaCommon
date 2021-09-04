package com.jef.servlet;

import com.jef.constant.BasicConstant;
import com.jef.common.utils.GraphicHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码管理
 * 将验证码放入session中，并从session中获取
 */
public class VerifyCodeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VerifyCodeServlet.class);

	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 获得当前请求对应的会话对象
	    HttpSession session = request.getSession();
	    // 图片宽度
	    final int width = 180;
	    // 图片高度
	    final int height = 40;
	    // 指定图片格式 (不是指MIME类型)
	    final String imgType = "jpeg";
	    // 获得可以向客户端返回图片的输出流// (字节流)
	    final OutputStream output = response.getOutputStream();
	    // 创建验证码图片并返回图片上的字符串  
	    String code = GraphicHelper.create(width, height, imgType, output);  
	    logger.info("验证码={}", code);
	    // 建立 uri 和 相应的 验证码 的关联 ( 存储到当前会话对象的属性中 )  
	    session.setAttribute(BasicConstant.VERFYCODE, code);
	}
}