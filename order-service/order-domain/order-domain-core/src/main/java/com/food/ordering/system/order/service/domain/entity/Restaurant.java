package com.food.ordering.system.order.service.domain.entity;

import java.util.List;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import java.util.Collections;

public class Restaurant extends AggregateRoot<RestaurantId> {
	private final List<Product> products;
	private boolean active;

	private Restaurant(Builder builder) {
		super.setId(builder.restaurantId);
		this.products = builder.products;
		this.active = builder.active;
	}

	public List<Product> getProducts() {
		return products;
	}

	public boolean isActive() {
		return active;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private RestaurantId restaurantId;
		private List<Product> products = Collections.emptyList();
		private boolean active;

		private Builder() {
		}

		public Builder RestaurantId(RestaurantId val) {
			this.restaurantId = val;
			return this;
		}

		public Builder Products(List<Product> products) {
			this.products = products;
			return this;
		}

		public Builder Active(boolean active) {
			this.active = active;
			return this;
		}

		public Restaurant build() {
			return new Restaurant(this);
		}
	}

}
