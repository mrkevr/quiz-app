package dev.mrkevr.quizapp.shell.command;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import dev.mrkevr.quizapp.api.model.Category;
import dev.mrkevr.quizapp.api.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryCommands {
	
	CategoryRepository categoryRepo;
	
	@ShellMethod(value = "View all categories", key = "categories")
	String categories() {
		
		List<Category> categories = categoryRepo.findAll();
		
		AsciiTable table = new AsciiTable();
		table.addRule();
		AT_Row titleRow = table.addRow(null, "Categories");
		titleRow.setTextAlignment(TextAlignment.CENTER);
		table.addRule();
		AT_Row heading = table.addRow("ID", "Category Name");
		heading.setTextAlignment(TextAlignment.CENTER);
		
		categories.forEach(c -> {
			table.addRule();
			table.addRow(c.getCategoryId(), c.getName());
		});
		table.addRule();
		
		table.getRenderer().setCWC(new CWC_LongestLine());
		return table.render(60);
	}
	
	@ShellMethod(value = "Add new categories", key = "categories add")
	String saveCategory(@NotNull String name) {
		
		Category category = new Category();
		category.setName(name);
		Category savedCategory = categoryRepo.save(category);
		
		AsciiTable table = new AsciiTable();
		table.addRule();
		AT_Row titleRow = table.addRow(null, "Category Saved");
		titleRow.setTextAlignment(TextAlignment.CENTER);
		table.addRule();
		table.addRow("ID", savedCategory.getCategoryId());
		table.addRule();
		table.addRow("Name", savedCategory.getName());
		table.addRule();
		
		table.getRenderer().setCWC(new CWC_LongestLine());
		return table.render(60);
	}
}
