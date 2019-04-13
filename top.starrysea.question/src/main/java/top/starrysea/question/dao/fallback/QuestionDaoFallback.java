package top.starrysea.question.dao.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.starrysea.question.dao.IQuestionDao;
import top.starrysea.question.object.dto.Question;

public class QuestionDaoFallback implements IQuestionDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public QuestionDaoFallback(Throwable cause) {
		logger.error(cause.getMessage(), cause);
	}

	@Override
	public List<Question> getAllQuestionDao(Question question, int page) {
		return null;
	}

	@Override
	public Boolean saveQuestionDao(Question question) {
		return null;
	}

	@Override
	public Boolean updateQuestionDao(Question question) {
		return false;
	}

	@Override
	public Integer getQuestionCountDao(Question question) {
		return null;
	}

}
