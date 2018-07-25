package com.danfeng.service.impl;

import java.util.List;
import java.util.function.DoubleToIntFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danfeng.entity.CollectAddressEntity;
import com.danfeng.entity.CollectItemEntity;
import com.danfeng.entity.OrderEntity;
import com.danfeng.entity.UserInfoEntity;
import com.danfeng.mapper.CollectAddressMapper;
import com.danfeng.mapper.CollectItemMapper;
import com.danfeng.mapper.GoodsMapper;
import com.danfeng.mapper.OrderMapper;
import com.danfeng.mapper.UserInfoMapper;
import com.danfeng.service.CollectAddressService;
import com.danfeng.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private CollectItemMapper collectItemMapper;
	
	@Override
	public List<OrderEntity> getOrderByUserId(Integer userId) {
		
		List<OrderEntity> result=null;
		try {
			result =orderMapper.getOrderByUserId(userId);
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return result;
	}
	
	@Override
	public List<OrderEntity> getAllOrder() {
		
		List<OrderEntity> result=null;
		try {
			result =orderMapper.getAllOrder();
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return result;
	}
	
	@Override
	public Integer getOrderCount(Integer userId) {
		
		Integer result=null;
		try {
			result =orderMapper.getOrderCount(userId);
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return result;
	}
	
	@Override
	public Integer getBusinessWaitTakingOrderCount(Integer userId) {
		
		Integer result=null;
		try {
			result =orderMapper.getBusinessWaitTakingOrderCount(userId);
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return result;
	}
	
	@Override
	public Integer getBusinessWaitHomeOrderCount(Integer userId) {
		
		Integer result=null;
		try {
			result =orderMapper.getBusinessWaitHomeOrderCount(userId);
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return result;
	}
	
	@Override
	public OrderEntity getOrderByOrderId(Integer orderId) {
		
		OrderEntity result=null;
		try {
			result =orderMapper.getOrderByOrderId(orderId);
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
	
		return result;
	}
	
	@Override
	@Transactional
	public void insert(OrderEntity orderEntity) {
		int effectedNum=0;
		try {
			
			effectedNum=orderMapper.insert(orderEntity);
			
		} catch (Exception e) {
			throw new RuntimeException("预约失败");
		}
		if(effectedNum<=0) throw new RuntimeException("预约失败");
		
	}
	
	@Override
	@Transactional
	public void update(OrderEntity orderEntity) {
		int effectedNum=0;
		try {
			effectedNum=orderMapper.update(orderEntity);
		} catch (Exception e) {
			throw new RuntimeException("更新订单失败");
		}
		if(effectedNum<=0) throw new RuntimeException("增加地址失败");
		
	}
	
	@Override
	@Transactional
	public void takingOrder(OrderEntity orderEntity) {
		int effectedNum=0;
		try {
			effectedNum=orderMapper.takingOrder(orderEntity);
		} catch (Exception e) {
			throw new RuntimeException("接单失败");
		}
		if(effectedNum<=0) throw new RuntimeException("接单失败");
		
	}
	
	//结算账单，保存商品列表
	@Override
	@Transactional
	public void settlementOrder(OrderEntity orderEntity) {
		
		
		int effectedNum=1;
		List<CollectItemEntity> collectItemList=orderEntity.getCollectItem();
		try {
			for(int i=0;i<collectItemList.size();i++){
				CollectItemEntity temp=collectItemList.get(i);
				if(collectItemMapper.queryRepeat(temp)<1){
					effectedNum=effectedNum*collectItemMapper.insert(temp);//无重复插入
				}
					
				else{
					effectedNum=effectedNum*collectItemMapper.update(temp)>0?1:0;//有重复更新
					System.out.println("更新");
				}									
			}
			//effectedNum=effectedNum*orderMapper.update(orderEntity);
		} catch (Exception e) {
			throw new RuntimeException("结算失败");
		}
		if(effectedNum<=0) throw new RuntimeException("结算失败");
		
	}
	
	//完成订单，给用户转账
	@Override
	@Transactional
	public void accomplishOrder(OrderEntity orderEntity) {
		
		
		int effectedNum=1;
		
		OrderEntity tempOrderEntity=orderMapper.getOrderByOrderId(orderEntity.getOrderId());
		if(tempOrderEntity.getState()!=2)
			throw new RuntimeException("订单状态异常");
		
		try {
			effectedNum=orderMapper.accomplishOrder(orderEntity);
			
			
			UserInfoEntity userInfoEntity = userInfoMapper.selectUserInfoByUserId(tempOrderEntity.getUserId());
			double oldMoney=userInfoEntity.getMoney();
			
			double orderMoney=0;
			List<CollectItemEntity> collectItemList=tempOrderEntity.getCollectItem();
			for(int i=0;i<collectItemList.size();i++){
				Integer goodsId=collectItemList.get(i).getGoodsId();
				
				Double price=goodsMapper.selectGoodsByGoodsId(goodsId).getPrice();
				orderMoney+=collectItemList.get(i).getNumber()*price;
			}
			userInfoEntity.setMoney(oldMoney+orderMoney);
			userInfoMapper.update(userInfoEntity);
			orderMapper.accomplishOrder(orderEntity);
		} catch (Exception e) {
			throw new RuntimeException("完成订单失败");
		}
		if(effectedNum<=0) throw new RuntimeException("完成订单失败");
		
	}

	@Override
	@Transactional
	public void delete(OrderEntity orderEntity) {
		int effectedNum=0;
		try {
			
			effectedNum=orderMapper.delete(orderEntity);
		} catch (Exception e) {
			throw new RuntimeException("删除失败");
		}
		if(effectedNum<=0) throw new RuntimeException("删除失败");
		
	}
	
	
	

}
