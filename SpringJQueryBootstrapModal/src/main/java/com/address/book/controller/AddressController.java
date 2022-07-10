package com.address.book.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.address.book.form.AddressbookForm;
import com.address.book.model.Addressbook;
import com.address.book.repository.AddressbookRepository;

@Controller
public class AddressController {

	@Autowired
	AddressbookRepository addBookRepo;

	@GetMapping("/")
	public String showList(Model model, @RequestParam(defaultValue = "0") int page) {
		model.addAttribute("data", addBookRepo.findAll(PageRequest.of(page, 5, Sort.by(Order.asc("id")))));
		model.addAttribute("currentPage", page);
		return "index";
	}

	@GetMapping("/list")
	public String showTheList(Model model, @RequestParam(defaultValue = "0") int page) {
		model.addAttribute("data", addBookRepo.findAll(PageRequest.of(page, 5, Sort.by(Order.asc("id")))));
		model.addAttribute("currentPage", page);
		return "index2";
	}

	@GetMapping("/list3")
	public String showTheList3(Model model) {
		model.addAttribute("contacts", addBookRepo.findAll());
		return "index3";
	}

	@GetMapping("/list4")
	public String showTheList4(@ModelAttribute AddressbookForm form, Model model) {
		return "index4";
	}

	@PostMapping("/validate")
	public String validate(@ModelAttribute @Validated AddressbookForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
            return showTheList4(form, model);
        }

        // formの中身をコンソールで確認
        System.out.println(form);
        // login.htmlにリダイレクト
        return "redirect:/index4";
	}

	@RequestMapping("/contact/{id}")
	public String getContact(@PathVariable("id") Long id, Model model) {

		Optional<Addressbook> contact = addBookRepo.findById(id);
		model.addAttribute("contact", contact);
		return "index3 :: modalContents";
	}

	@PostMapping("/save")
	public String saveAddBook(Addressbook ab) {
		addBookRepo.save(ab);
		return "redirect:/";
	}

	@PostMapping("/save2")
	public String saveAddBook2(Addressbook ab) {
		addBookRepo.save(ab);
		return "redirect:/list";
	}

	@PostMapping("/save_async")
	@ResponseBody
	public String saveAsync(@Valid @ModelAttribute AddressbookForm addressbookForm, BindingResult result, Model model) {
		model.addAttribute("addressbookForm", addressbookForm);
		if (result.hasErrors()) {
			// return "index2 :: formfrag";
			return "view_name :: fragmentName";
		}

		Addressbook ab = new Addressbook(addressbookForm.getId(), addressbookForm.getFirstname(),
				addressbookForm.getLastname(), addressbookForm.getPhonenumber(), addressbookForm.getEmail(),
				addressbookForm.getAddress());
		Addressbook addbook = addBookRepo.save(ab);
		System.out.println(addbook);
		return "true";
	}

	@GetMapping("/delete")
	public String deleteAddBook(Long id) {
		addBookRepo.deleteById(id);
		return "redirect:/";
	}

	@GetMapping("/findOne")
	@ResponseBody
	public Optional<Addressbook> findOne(Long id) {
		return addBookRepo.findById(id);
	}

}
