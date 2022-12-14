package com.ben.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.ben.bookclub.models.Book;
import com.ben.bookclub.models.User;
import com.ben.bookclub.services.BookService;
import com.ben.bookclub.services.UserService;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	// ---------- CREATE -----------------//
	
	@GetMapping("/books/new")
	public String newBook(
		@ModelAttribute("bookObj") Book emptyBook,
		HttpSession session
	) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		return "/books/new.jsp";
	}
	
	@PostMapping("/books/new")
	public String processBook(
		@Valid @ModelAttribute("bookObj") Book filledBook,
		BindingResult results
	) {
		// VALIDATIONS FAIL
		if(results.hasErrors()) {
			return "/books/new.jsp";
		}
		Book newBook = bookService.create(filledBook);
		return "redirect:/books";
//		return "redirect:/books/" + newBook.getId();
	}
	
//	---------- READ ONE ---------------//
	@GetMapping("/books/{id}")
	public String oneBook(
			@PathVariable("id") Long id, 
			Model model
			) {
		model.addAttribute("oneBook", bookService.getOne(id));
		return "/books/show.jsp";
	}
	
// ----------- READ ALL ---------------//
	@GetMapping("/books")
	public String index(
			HttpSession session, 
			Model model
			) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		Long user_id = (Long) session.getAttribute("user_id");
		User user = userService.getOneUser(user_id);
		model.addAttribute("user", user);
		model.addAttribute("allBooks", bookService.getAll());
		return "/books/dashboard.jsp";
		
	}
		
	// ---------- UPDATE --------------//
	@GetMapping("/books/{id}/edit")
	public String edit(
		@PathVariable("id") Long id,
		Model model
	) {
		model.addAttribute("bookObj", bookService.getOne(id));
		return "/books/edit.jsp";
	}
	@PutMapping("/books/{id}/edit")
	public String update(
		@Valid @ModelAttribute("bookObj") Book filledBook,
		BindingResult results
	) {
		if(results.hasErrors()) {
			return "/books/new.jsp";
		}
		bookService.create(filledBook);
		return "redirect:/books";
	}
	// ---------- UPDATE --------------//
	
	// ---------- DELETE --------------//
	@GetMapping("/books/{id}/delete")
	public String delete(
		@PathVariable("id") Long id
	) {
		bookService.deleteOne(id);
		return "redirect:/books";
	}
	@DeleteMapping("/books/{id}")
	public String deleteBook(
		@PathVariable("id") Long id
	) {
		bookService.deleteOne(id);
		return "redirect:/books";
	}
	// ---------- DELETE --------------//
}
