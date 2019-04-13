package top.starrysea.dal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.starrysea.common.Condition;
import top.starrysea.dal.dao.IQuestionDao;
import top.starrysea.dal.entity.Question;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private IQuestionDao questionDao;

	@PostMapping("/all")
	public List<Question> getAllQuestionDao(@RequestBody Question question, @RequestParam("page") int page) {
		return questionDao.getAllQuestionDao(new Condition(page), question).getResult(List.class);
	}

	@PostMapping("/save")
	public Boolean saveQuestionDao(@RequestBody Question question) {
		return questionDao.saveQuestionDao(question).isSuccessed();
	}

	@PostMapping("/update")
	public Boolean updateQuestionDao(@RequestBody Question question) {
		return questionDao.updateQuestionDao(question).isSuccessed();
	}

	@PostMapping("/count")
	public Integer getQuestionCountDao(@RequestBody Question question) {
		return questionDao.getQuestionCountDao(question).getResult(Integer.class);
	}
}
