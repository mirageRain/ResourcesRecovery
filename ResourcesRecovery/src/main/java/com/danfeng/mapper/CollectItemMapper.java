package com.danfeng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.danfeng.entity.CollectItemEntity;
import com.danfeng.entity.GoodsEntity;

public interface CollectItemMapper {

	@Select("select * from collect_item where order_id = #{orderId}")
	@Results({ @Result(id = true, property = "collectItemId", column = "collect_item_id"),
			@Result(property = "orderId", column = "order_id"), @Result(property = "goodsId", column = "goods_id"),
			@Result(property = "number", column = "number") })
	List<CollectItemEntity> selectCollectItemByOrderId(Integer orderId);

	@Insert("INSERT INTO collect_item(order_id,goods_id,number) " + "VALUES(#{orderId}, #{goodsId}, #{number})")
	int insert(CollectItemEntity collectItemEntity);
	
	@Insert("UPDATE collect_item SET number= #{number} where order_id=#{orderId} and goods_id=#{goodsId}")
	int update(CollectItemEntity collectItemEntity);
	
	//查询订单废品列表中某种废品的重复数
	@Select("select count(collect_item_id) from collect_item where order_id = #{orderId} and goods_id=#{goodsId} ")
	int queryRepeat(CollectItemEntity collectItemEntity);
}
