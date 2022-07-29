package com.food.ordering.system.order.service.dataaccess.restaurant.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RestaurantEntityId.class)
@Table(name = "order_restaurant_m_view", schema = "restaurant")
@Entity
public class RestaurantEntity {

	@Id
	private UUID restaurantId;

	@Id
	private UUID productId;
	
	private String restaurantName; 
	
	private Boolean restaurantActive;
	
	private String productName;
	
	private BigDecimal productPrice;

	@Override
	public int hashCode() {
		return Objects.hash(productId, restaurantId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestaurantEntity other = (RestaurantEntity) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(restaurantId, other.restaurantId);
	}
	
	
	
}
