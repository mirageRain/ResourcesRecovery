package com.danfeng.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danfeng.dto.GoodsDto;
import com.danfeng.dto.GoodsUnitDto;
import com.danfeng.entity.GoodsEntity;
import com.danfeng.entity.GoodsUnitEntity;
import com.danfeng.security.MyUser;
import com.danfeng.service.impl.GoodsServiceImpl;
import com.danfeng.service.impl.GoodsUnitServiceImpl;
import com.danfeng.util.FileUtil;
import com.danfeng.util.ImagesUtiil;
import com.sun.imageio.plugins.common.ImageUtil;

@RestController
@RequestMapping("/admin/goods")
public class AdminGoodsController {

	@Autowired
	private GoodsUnitServiceImpl goodsUnitServiceImpl;

	@Autowired
	private GoodsServiceImpl goodsServiceImpl;

	// 增加规格
	@PostMapping("")
	public Map<String, Object> getCollectAddress(@Valid @RequestBody GoodsDto goodsDto, BindingResult errors) {

		Map<String, Object> map = new HashMap<>();
		GoodsEntity goodsEntity = new GoodsEntity();
		String fileName = FileUtil.getRandomFileName();
		String base64Img=goodsDto.getimageBase64();
		if (errors.getErrorCount() > 0) {
			map.put("success", false);
			map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
			return map;
		}
		if(base64Img.startsWith("data:image/jpeg;base64,"))
			base64Img=base64Img.substring(23, base64Img.length());
		else {
			map.put("success", false);
			map.put("errMsg", "图片格式不对失败");
			return map;
		}
		try {

			
			if (ImagesUtiil.GenerateImage(FileUtil.getImgBasePath() + fileName + ".jpg", base64Img)) {

			} else {
				map.put("success", false);
				map.put("errMsg", "文件转码失败");
				return map;
			}

		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", "文件转码失败");
			return map;
		}

		try {
			goodsEntity.setChildTypeId(goodsDto.getChildTypeId());
			goodsEntity.setEnable(1);
			goodsEntity.setUnitId(goodsDto.getUnitId());
			goodsEntity.setImageUrl(fileName + ".jpg");
			goodsEntity.setName(goodsDto.getName());
			goodsEntity.setPrice(goodsDto.getPrice());

		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", "数据格式错误");
			return map;
		}

		try {
			goodsServiceImpl.insert(goodsEntity);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}

		map.put("success", true);
		return map;
	}

	@GetMapping("")
	public Map<String, Object> getGoodsList(@AuthenticationPrincipal MyUser user) {
		Map<String, Object> map = new HashMap<>();
		List<GoodsEntity> goodsList = null;
		try {
			goodsList = goodsServiceImpl.getGoodsList();
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		map.put("data", goodsList);
		return map;
	}

	@PutMapping("/{goodsId:\\d+}")
	public Map<String, Object> updateCollectAddress(@Valid @RequestBody GoodsDto goodsDto, BindingResult errors,
			@AuthenticationPrincipal MyUser user, @PathVariable Integer goodsId) {

		Map<String, Object> map = new HashMap<>();
		GoodsEntity goodsEntity = new GoodsEntity();
		String fileName = FileUtil.getRandomFileName();
		String base64Img=goodsDto.getimageBase64();
		if (errors.getErrorCount() > 0) {
			map.put("success", false);
			map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
			return map;
		}
		if(base64Img.startsWith("data:image/jpeg;base64,"))
			base64Img=base64Img.substring(23, base64Img.length());
		else {
			map.put("success", false);
			map.put("errMsg", "图片格式不对失败");
			return map;
		}
		System.out.println(base64Img);
		try {

			if (ImagesUtiil.GenerateImage(FileUtil.getImgBasePath() + fileName + ".jpg", base64Img)) {

			} else {
				map.put("success", false);
				map.put("errMsg", "文件转码失败");
				return map;
			}

		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", "文件转码失败");
			return map;
		}

		try {
			goodsEntity.setGoodsId(goodsId);
			goodsEntity.setChildTypeId(goodsDto.getChildTypeId());
			goodsEntity.setEnable(1);
			goodsEntity.setUnitId(goodsDto.getUnitId());
			goodsEntity.setImageUrl(fileName+".jpg");
			goodsEntity.setName(goodsDto.getName());
			goodsEntity.setPrice(goodsDto.getPrice());

		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", "数据格式错误");
			return map;
		}

		try {
			goodsServiceImpl.update(goodsEntity);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}

		map.put("success", true);
		return map;
	}

	// 删除地址
	@DeleteMapping("/{goodsId:\\d+}")
	public Map<String, Object> deleteCollectAddress(@AuthenticationPrincipal MyUser user,
			@PathVariable Integer goodsId) {
		Map<String, Object> map = new HashMap<>();

		if (goodsId == null || goodsId <= 0) {
			map.put("success", false);
			map.put("errMsg", "废品ID不合法");
			return map;
		}
		try {
			goodsServiceImpl.delete(goodsId);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		return map;
	}

}
