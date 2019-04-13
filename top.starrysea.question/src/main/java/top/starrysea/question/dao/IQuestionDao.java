package top.starrysea.question.dao;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import feign.hystrix.FallbackFactory;
import top.starrysea.question.dao.fallback.QuestionDaoFallback;
import top.starrysea.question.object.dto.Question;

@FeignClient(name = "starrysea-dal", fallbackFactory = QuestionDaoFallbackFactory.class)
public interface IQuestionDao {
	@RequestMapping(value = "/question/all", method = RequestMethod.POST)
	public List<Question> getAllQuestionDao(@RequestBody Question question, @RequestParam("page") int page);

	@RequestMapping(value = "/question/save", method = RequestMethod.POST)
	public Boolean saveQuestionDao(@RequestBody Question question);

	@RequestMapping(value = "/question/update", method = RequestMethod.POST)
	public Boolean updateQuestionDao(@RequestBody Question question);

	@RequestMapping(value = "/question/count")
	public Integer getQuestionCountDao(@RequestBody Question question);
}

@Component
class QuestionDaoFallbackFactory implements FallbackFactory<IQuestionDao> {

	@Override
	public IQuestionDao create(Throwable cause) {
		return new QuestionDaoFallback(cause);
	}

}
