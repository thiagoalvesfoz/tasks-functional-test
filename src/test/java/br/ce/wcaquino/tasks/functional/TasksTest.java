package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TasksTest {

	public WebDriver acessarApplicacao() {
		System.setProperty("webdriver.edge.driver",
				"C:\\Users\\Usuário\\Documents\\dev\\devops\\driverChromium\\msedgedriver.exe");

		WebDriver driver = new EdgeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	@Test
	public void deveSalvarTarefasComSucesso() {

		WebDriver driver = acessarApplicacao();

		try {
			// launching the specified URL
			driver.get("http://localhost:8001/tasks");

			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);

		} finally {
			// fechar o browser
			driver.close();
		}
	}

	@Test
	public void naoDeveSalvarTarefasSemDescricao() {

		WebDriver driver = acessarApplicacao();

		try {
			// launching the specified URL
			driver.get("http://localhost:8001/tasks");

			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de erro
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);

		} finally {
			// fechar o browser
			driver.close();
		}
	}

	@Test
	public void naoDeveSalvarTarefasSemData() {

		WebDriver driver = acessarApplicacao();

		try {
			// launching the specified URL
			driver.get("http://localhost:8001/tasks");

			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de erro
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);

		} finally {
			// fechar o browser
			driver.close();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefasComDataPassada() {

		WebDriver driver = acessarApplicacao();

		try {
			// launching the specified URL
			driver.get("http://localhost:8001/tasks");

			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de erro
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);

		} finally {
			// fechar o browser
			driver.close();
		}
	}
}