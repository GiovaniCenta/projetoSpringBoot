package com.example.demo.entidades.enums;

public enum OrderStatus {
	AGUARDANDO_PAGAMENTO(1), //fazer desse metodo é melhor para manutenção, pois se inserir outro enum não sera alterado os outros automaticamente
	PAGO(2),
	ENVIADO(3),
	ENTREGUE(4),
	CANCELADO(5);
	
	private int code; //porem tem que se fazer um get e um set para poder ser utilizado
	private OrderStatus(int code) {
		this.code=code;
	}
	public int getCode() {
		return code;
	}
	
	public static OrderStatus valueOf(int code) {
		for (OrderStatus value: OrderStatus.values()) {
			if (value.getCode()==code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo de status do pedido invalido!");
	}
}
