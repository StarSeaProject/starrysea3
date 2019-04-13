package top.starrysea.file.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import top.starrysea.file.handler.FileCondition;
import top.starrysea.file.handler.FileType;
import top.starrysea.file.handler.FileUtil;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileUtil fileUtil;

	@PostMapping(value="/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String saveFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
		return fileUtil.saveFile(file, FileCondition.of(FileType.IMG, 1d, "YE"));
	}
}
