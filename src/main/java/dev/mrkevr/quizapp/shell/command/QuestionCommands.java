package dev.mrkevr.quizapp.shell.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import de.vandermeer.asciitable.AsciiTable;
import dev.mrkevr.quizapp.api.model.Option;
import dev.mrkevr.quizapp.api.model.Question;
import dev.mrkevr.quizapp.api.repository.QuestionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionCommands {
	
	QuestionRepository questionRepo;
	
	@ShellMethod(value = "Get question by id", key = "question")
	String question(@NotNull String id) {

		Optional<Question> optional = questionRepo.findById(id);

		if (optional.isEmpty()) {
			return "No question found with that id";
		} else {
			Question question = optional.get();
			List<String> options = convertOptions(question.getOption());
			
			AsciiTable table = new AsciiTable();
			table.addRule();
			table.addRow(null, question.getQuestion());
			table.addRule();
			table.addRow(options.get(0), options.get(1));
			table.addRule();
			table.addRow(options.get(2), options.get(3));
			table.addRule();
			
			return table.render();
		}
	}

	private List<String> convertOptions(Option option) {
		List<String> list = new ArrayList<>();
		list.add("(A) " + option.getA());
		list.add("(B) " + option.getB());
		list.add("(C) " + option.getC());
		list.add("(D) " + option.getD());
		return list;
	}
}
