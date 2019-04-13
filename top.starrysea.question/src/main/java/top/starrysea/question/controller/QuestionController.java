package top.starrysea.question.controller;

import static top.starrysea.common.ResultKey.LIST_1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.starrysea.common.ModelAndViewFactory;
import top.starrysea.common.ServiceResult;
import top.starrysea.question.object.view.in.QuestionForAnswer;
import top.starrysea.question.object.view.in.QuestionForAsk;
import top.starrysea.question.object.dto.Question;
import top.starrysea.question.object.view.in.QuestionForAll;
import top.starrysea.question.service.IQuestionService;

@Controller
public class QuestionController {
	@Autowired
	private IQuestionService questionService;

	@GetMapping("/index")
	public ModelAndView queryQuestionController(QuestionForAll question) {
		question.setQuestionStatus((short) 2);
		ServiceResult serviceResult = questionService.queryAllQuestionService(question.getCondition(),
				question.toDTO());
		List<Question> result = serviceResult.getResult(LIST_1);
		List<top.starrysea.question.object.view.out.QuestionForAll> voResult = result.stream().map(Question::toVoForAll)
				.collect(Collectors.toList());
		ModelAndView modelAndView = new ModelAndView("question");
		modelAndView.addObject("result", voResult);
		modelAndView.addObject("nowPage", serviceResult.getNowPage());
		modelAndView.addObject("totalPage", serviceResult.getTotalPage());
		return modelAndView;
	}

	@PostMapping("/ajax")
	@ResponseBody
	public Map<String, Object> queryQuestionControllerAjax(@RequestBody QuestionForAll question) {
		ServiceResult serviceResult = questionService.queryAllQuestionService(question.getCondition(),
				question.toDTO());
		List<Question> result = serviceResult.getResult(LIST_1);
		List<top.starrysea.question.object.view.out.QuestionForAll> voResult = result.stream().map(Question::toVoForAll)
				.collect(Collectors.toList());
		Map<String, Object> theResult = new HashMap<>();
		theResult.put("result", voResult);
		theResult.put("nowPage", serviceResult.getNowPage());
		theResult.put("totalPage", serviceResult.getTotalPage());
		return theResult;
	}

	@PostMapping("/ask")
	public ModelAndView askQuestionController(@Valid QuestionForAsk question, BindingResult bindingResult,
			Device device) {
		questionService.askQuestionService(question.toDTO());
		return ModelAndViewFactory.newSuccessMav("提问成功！", device);
	}

	@PostMapping("/answer")
	@ResponseBody
	public Map<String, Object> answerQuestionController(@RequestBody @Valid QuestionForAnswer question,
			BindingResult bindingResult) {
		questionService.answerQuestionService(question.toDto());
		Map<String, Object> theResult = new HashMap<>();
		theResult.put("result", true);
		return theResult;
	}
}
