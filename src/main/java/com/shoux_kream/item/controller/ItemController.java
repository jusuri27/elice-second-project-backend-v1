package com.shoux_kream.item.controller;


import com.shoux_kream.item.dto.response.ItemResponse;
import com.shoux_kream.item.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 모든 인증된 사용자가 접근 가능 (유저 및 관리자) - 모든 상품의 목록을 조회하여 응답
    @GetMapping("/item-list")
    public String getItems(Model model) {
        List<ItemResponse> items = itemService.findAll();
        model.addAttribute("items", items);
        return "item/item-list";
    }

    // 상품을 클릭했을때 나오는 상세 페이지(ex.발매가, 사이즈 선택)
    @GetMapping("/item-detail/{id}")
    public String getItemPage(@PathVariable("id") Long id, Model model) {
        ItemResponse item = itemService.findById(id); // 특정 ID의 상품을 조회
        model.addAttribute("item", item);
        return "item/item-detail";
    }

    @GetMapping("/item-add") // GET 요청으로 상품 등록 페이지를 불러오는 메서드
    public String getItemAddPage(){
        return "item/item-add";
    }

    // 상품 수정 페이지 뷰
    @GetMapping("/edit/{id}")
    public String showEditItemPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("itemId", id);
        return "item/item-edit";
    }
}