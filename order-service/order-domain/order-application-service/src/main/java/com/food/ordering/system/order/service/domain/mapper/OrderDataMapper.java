package com.food.ordering.system.order.service.domain.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;

@Component
public class OrderDataMapper {

	public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
		return Restaurant.builder().RestaurantId(new RestaurantId(createOrderCommand.getCustomerId()))
				.Products(createOrderCommand.getItems().stream()
						.map(orderItems -> new Product(new ProductId(orderItems.getProductId())))
						.collect(Collectors.toList()))
				.build();
	}

	public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
		return Order.builder().CustomerId(new CustomerId(createOrderCommand.getCustomerId()))
				.RestaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
				.DeliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
				.Price(new Money(createOrderCommand.getPrice()))
				.Items(orderItemsToOrderItemEntities(createOrderCommand.getItems())).build();

	}

	public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
		return CreateOrderResponse.builder().orderTrackingId(order.getTrackingId().getValue())
				.orderStatus(order.getOrderStatus()).message(message).build();
	}

	private List<OrderItem> orderItemsToOrderItemEntities(
			List<com.food.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {

		return orderItems.stream()
				.map(orderItem -> OrderItem.builder().Product(new Product(new ProductId(orderItem.getProductId())))
						.Price(new Money(orderItem.getPrice())).Quantity(orderItem.getQuantity())
						.SubTotal(new Money(orderItem.getSubTotal())).build())
				.collect(Collectors.toList());
	}

	private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
		// TODO Auto-generated method stub
		return new StreetAddress(UUID.randomUUID(), orderAddress.getStreet(), orderAddress.getPostalCode(),
				orderAddress.getCity());
	}
}
