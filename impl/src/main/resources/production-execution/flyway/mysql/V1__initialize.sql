create table pde_production_execution (
	id binary(16) not null,
	end_date datetime,
	error_quantity decimal(19,2),
	executed_date datetime,
	executor_id varchar(50),
	item_id binary(16),
	item_spec_code varchar(20),
	order_id binary(16),
	process_id binary(16),
	quantity decimal(19,2),
	start_date datetime,
	unit varchar(20),
	primary key (id)
) engine=InnoDB;

create index IDX7x1rp8y0s5rky5ifeia3f5g2y
	on pde_production_execution (order_id);
