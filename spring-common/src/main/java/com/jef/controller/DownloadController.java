package com.jef.controller;

import com.jef.entity.BaseJSONVo;
import com.jef.util.REJSONUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载接口
 *
 * @author Jef
 * @date 2021/7/9
 */
@Controller
@RequestMapping(value = "/download")
public class DownloadController {

    private static final Logger logger = LogManager.getLogger(PostController.class);

    @RequestMapping(value = "/download")
    public ModelAndView introduce(ModelAndView mv) {
        mv.setViewName("download/download");
        return mv;
    }

    @RequestMapping(value = "/getUrlTransferBase64", method = RequestMethod.GET)
    @ResponseBody
    public BaseJSONVo getUrlTransferBase64(@RequestParam(value = "url") String url) throws IOException {
        return REJSONUtils.success("", 0, "操作成功");
    }

}