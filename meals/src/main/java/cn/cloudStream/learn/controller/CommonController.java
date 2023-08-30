package cn.cloudStream.learn.controller;

import cn.cloudStream.learn.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${img.path}")
    private String imagePath;

    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        log.info(file.toString());
        String originalFilename = file.getOriginalFilename();//abc.jpg
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;//dfsdfdfd.jpg
        File imgDir = new File(imagePath);
        if (!imgDir.exists()) {
            imgDir.mkdir();
        }
        try {
            file.transferTo(new File(imagePath + fileName));
        } catch (Exception e) {

        }
        return R.success(fileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try {
            FileInputStream in = new FileInputStream(new File(imagePath + name));
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("image/jpeg");
            int size =in.available();
            int len = 0;
            byte[] bytes = new byte[size];
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
                out.flush();
            }
            //关闭资源
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
