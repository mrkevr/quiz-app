package dev.mrkevr.quizapp.shell.command;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import dev.mrkevr.quizapp.api.model.Ranking;
import dev.mrkevr.quizapp.api.repository.RankingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RankingCommands {
	
	RankingRepository rankingRepository;
	DecimalFormat decimalFormat = new DecimalFormat("0.00");
	
	@ShellMethod(value = "Get ranking by category id", key = "ranking")
	String ranking(@NotNull String id) {

		Optional<Ranking> optional = rankingRepository.findByCategoryId(id);

		if (optional.isEmpty()) {
			return "No ranking found with that category id";
		} else {
			Ranking ranking = optional.get();

			AsciiTable table = new AsciiTable();
			table.addRule();
			AT_Row titleRow = table.addRow(null, ranking.getCategoryName() + " Ranking");
			titleRow.setTextAlignment(TextAlignment.CENTER);
			table.addRule();
			AT_Row columnNamesRow = table.addRow("Username", "Score");
			columnNamesRow.setTextAlignment(TextAlignment.CENTER);

			Map<String, Double> sortedMap = ranking.getUsernamePercentage().entrySet().stream()
					.sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
					.limit(10)
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

			sortedMap.forEach((username, score) -> {
				table.addRule();
				AT_Row row = table.addRow(username, decimalFormat.format(score));
				row.getCells().get(1).getContext().setTextAlignment(TextAlignment.RIGHT);
			});
			
			table.addRule();
			table.getRenderer().setCWC(new CWC_LongestLine());
			return table.render(60);
		}
	}
}
