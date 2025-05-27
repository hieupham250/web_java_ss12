package com.example.ss12.controller;

import com.example.ss12.model.Bus;
import com.example.ss12.model.Seat;
import com.example.ss12.service.BusService;
import com.example.ss12.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private SeatService seatService;

    @GetMapping
    public String listBuses(Model model) {
        List<Bus> buses = busService.findAll();
        model.addAttribute("buses", buses);
        return "busList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "addBus";
    }

    @PostMapping("/add")
    public String addBus(@Valid @ModelAttribute("bus") Bus bus,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        if (bindingResult.hasErrors()) {
            return "addBus";
        }

        if (bus.getLicensePlate() == null || bus.getLicensePlate().trim().isEmpty()) {
            model.addAttribute("error", "Biển số xe không được để trống");
            return "addBus";
        }

        if (bus.getBusType() == null || bus.getBusType().trim().isEmpty()) {
            model.addAttribute("error", "Loại xe không được để trống");
            return "addBus";
        }

        if (bus.getRowSeat() <= 0 || bus.getColSeat() <= 0) {
            model.addAttribute("error", "Số hàng và cột ghế phải lớn hơn 0");
            return "addBus";
        }

        bus.setTotalSeat(bus.getRowSeat() * bus.getColSeat());

        if (bus.getImage() == null || bus.getImage().trim().isEmpty()) {
            bus.setImage("https://photo-cms-tpo.epicdn.me/w890/Uploaded/2023/rkznae/2020_04_05/qua_xoai_va_nhung_tac_dung_voi_suc_khoe_1_IMRD.jpg");
        }

        boolean result = busService.create(bus);

        if (result) {
            createSeatsForBus(bus);
            redirectAttributes.addFlashAttribute("success", "Thêm xe thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi thêm xe!");
        }

        return "redirect:/bus";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Bus bus = busService.findById(id);
        if (bus == null) {
            return "redirect:/bus";
        }
        model.addAttribute("bus", bus);
        return "editBus";
    }

    @PostMapping("/edit")
    public String editBus(@Valid @ModelAttribute("bus") Bus bus,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Model model) {

        if (bindingResult.hasErrors()) {
            return "editBus";
        }

        if (bus.getLicensePlate() == null || bus.getLicensePlate().trim().isEmpty()) {
            model.addAttribute("error", "Biển số xe không được để trống");
            return "editBus";
        }

        if (bus.getBusType() == null || bus.getBusType().trim().isEmpty()) {
            model.addAttribute("error", "Loại xe không được để trống");
            return "editBus";
        }

        if (bus.getRowSeat() <= 0 || bus.getColSeat() <= 0) {
            model.addAttribute("error", "Số hàng và cột ghế phải lớn hơn 0");
            return "editBus";
        }

        Bus oldBus = busService.findById(bus.getId());
        bus.setTotalSeat(bus.getRowSeat() * bus.getColSeat());
        boolean result = busService.update(bus);

        if (result) {
            if (oldBus.getRowSeat() != bus.getRowSeat() ||
                    oldBus.getColSeat() != bus.getColSeat() ||
                    !oldBus.getBusType().equals(bus.getBusType())) {

                seatService.deleteByBusId(bus.getId());
                createSeatsForBus(bus);
            }

            redirectAttributes.addFlashAttribute("success", "Cập nhật xe thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật xe!");
        }

        return "redirect:/bus";
    }

    @GetMapping("/delete/{id}")
    public String deleteBus(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        seatService.deleteByBusId(id);
        boolean result = busService.delete(id);

        if (result) {
            redirectAttributes.addFlashAttribute("success", "Xóa xe thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa xe!");
        }

        return "redirect:/bus";
    }

    @GetMapping("/detail/{id}")
    public String showBusDetail(@PathVariable("id") int id, Model model) {
        Bus bus = busService.findById(id);
        if (bus == null) {
            return "redirect:/bus";
        }

        List<Seat> seats = seatService.findByBusId(id);
        Seat[][] seatMatrix = new Seat[bus.getRowSeat()][bus.getColSeat()];

        for (Seat seat : seats) {
            String seatName = seat.getNameSeat();
            int row = seatName.charAt(0) - 'A';
            int col = Integer.parseInt(seatName.substring(1)) - 1;

            if (row >= 0 && row < bus.getRowSeat() && col >= 0 && col < bus.getColSeat()) {
                seatMatrix[row][col] = seat;
            }
        }

        model.addAttribute("bus", bus);
        model.addAttribute("seats", seats);
        model.addAttribute("seatMatrix", seatMatrix);

        return "detailBus";
    }

    private void createSeatsForBus(Bus bus) {
        BigDecimal seatPrice = getSeatPriceByType(bus.getBusType());

        for (int row = 1; row <= bus.getRowSeat(); row++) {
            for (int col = 1; col <= bus.getColSeat(); col++) {
                Seat seat = new Seat();
                seat.setNameSeat(String.valueOf((char)('A' + row - 1)) + col);
                seat.setPrice(seatPrice);
                seat.setBusId(bus.getId());
                seat.setStatus("available");
                seatService.save(seat);
            }
        }
    }

    private BigDecimal getSeatPriceByType(String busType) {
        switch (busType.toUpperCase()) {
            case "VIP":
                return new BigDecimal("150000");
            case "LUXURY":
                return new BigDecimal("200000");
            case "NORMAL":
            default:
                return new BigDecimal("100000");
        }
    }
}