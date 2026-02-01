package com.qingge.springboot.controller;

import com.qingge.springboot.utils.QRCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    /**
     * 根据 content 生成二维码
     * 测试：http://localhost/blog/portals/qrcode/getQRCode?content=www.hcshow.online&logoUrl=http://pic.51yuansu.com/pic3/cover/03/84/85/5c11a40a32429_610.jpg
     *
     * @param content
     * @return
     */
    @GetMapping("/getQRCodeBase64")
    public String getQRCode(@RequestParam("content") String content
                                   ) {
        String base64QRCode = QRCodeUtil.getBase64QRCode(content);
        System.out.println(base64QRCode);
        return base64QRCode;
    }

    /**
     * 根据 content 生成二维码
     * 测试：http://localhost/blog/portals/qrcode/getQRCode?content=www.hcshow.online
     */
    @GetMapping(value = "/getQRCode")
    public void getQRCode(HttpServletResponse response,
                          @RequestParam("content") String content,
                          @RequestParam(value = "logoUrl", required = false) String logoUrl) throws Exception {
        try (ServletOutputStream stream = response.getOutputStream();) {
            QRCodeUtil.getQRCode(content, logoUrl, stream);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

