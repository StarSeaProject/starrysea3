package top.starrysea.question.service;

import top.starrysea.common.Condition;
import top.starrysea.common.ServiceResult;
import top.starrysea.question.object.dto.Question;

public interface IQuestionService {
	ServiceResult queryAllQuestionService(Condition condition, Question question);

	ServiceResult askQuestionService(Question question);

	ServiceResult answerQuestionService(Question question);
}
