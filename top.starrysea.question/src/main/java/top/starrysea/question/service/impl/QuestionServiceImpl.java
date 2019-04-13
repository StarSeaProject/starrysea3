package top.starrysea.question.service.impl;

import static top.starrysea.common.ResultKey.LIST_1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.question.dao.IQuestionDao;
import top.starrysea.question.object.dto.Question;
import top.starrysea.question.service.IQuestionService;

@Service("questionService")
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private IQuestionDao questionDao;
	public static final int PAGE_LIMIT = 10;

	@Override
	public ServiceResult queryAllQuestionService(Condition condition, Question question) {
		ServiceResult result = new ServiceResult();
		List<Question> questionsList = questionDao.getAllQuestionDao(question, condition.getPage());
		int count = questionDao.getQuestionCountDao(question);
		int totalPage = 0;
		if (count % PAGE_LIMIT == 0) {
			totalPage = count / PAGE_LIMIT;
		} else {
			totalPage = (count / PAGE_LIMIT) + 1;
		}
		result.setSuccessed(true);
		result.setResult(LIST_1, questionsList);
		result.setNowPage(condition.getPage());
		result.setTotalPage(totalPage);
		return result;
	}

	@Override
	public ServiceResult askQuestionService(Question question) {
		questionDao.saveQuestionDao(question);
		return new ServiceResult(true);
	}

	@Override
	public ServiceResult answerQuestionService(Question question) {
		questionDao.updateQuestionDao(question);
		return new ServiceResult(true);
	}

}
