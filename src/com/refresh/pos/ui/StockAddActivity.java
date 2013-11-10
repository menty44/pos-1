//package com.refresh.pos.ui;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//import com.refresh.pos.R;
//import com.refresh.pos.core.Inventory;
//import com.refresh.pos.database.NoDaoSetException;
//
//public class StockAddActivity extends Activity{
//	private EditText itemBarcode;
//	private EditText itemName;
//	private EditText itemPrice;
//	private Inventory inventory;
//	private double quantity;
//
//	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//		//		Log.d("BARCODE", "BARCODE 'onActivityResult' Successfully.");
//
//		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
//				requestCode, resultCode, intent);
//		//
//		if (scanningResult != null) {
//			String scanContent = scanningResult.getContents();
//			String name = inventory.getProductCatalog().getProductByBarcode(scanContent).getName();
//			if(name.equals("UNDEFINED")){
//				Toast.makeText(StockAddActivity.this,
//						"Can not find " + scanContent +" in database", Toast.LENGTH_SHORT)
//						.show();
//			}
//			else{
//				itemBarcode.setText(scanContent);
//				itemName.setText(name);
//			}
//			//
//		} else {
//			Toast.makeText(StockAddActivity.this,
//					"Failed to retrieve barcode." + resultCode, Toast.LENGTH_SHORT)
//					.show();
//		}
//	}
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		try {
//			inventory = Inventory.getInstance();
//		} catch (NoDaoSetException e) {
//			e.printStackTrace();
//		}
//
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_stockadd);
//		final EditText quantityTxt = (EditText) findViewById(R.id.quantityTxt);
//		quantity = Double.parseDouble(quantityTxt.getText().toString());
//		itemName = (EditText) findViewById(R.id.nameTxt);
//		itemBarcode = (EditText) findViewById(R.id.barcodeTxt);
//				itemBarcode.setEnabled(false);
//				itemName.setEnabled(false);
//		itemPrice = (EditText) findViewById(R.id.costTxt);
//		final Button scanButton = (Button) findViewById(R.id.scanButton);
//		scanButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				IntentIntegrator scanIntegrator = new IntentIntegrator(StockAddActivity.this);
//				scanIntegrator.initiateScan();
//
//
//			}
//		}
//				);
//		final Button plusButton = (Button) findViewById(R.id.plus);
//		plusButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				quantity = Integer.parseInt(quantityTxt.getText().toString());
//				quantity++;
//				quantityTxt.setText(quantity+"");
//			}
//		});
//		final Button minButton = (Button) findViewById(R.id.min);
//		minButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if(quantity==0){
//
//				}
//				else{
//					quantity = Double.parseDouble(quantityTxt.getText().toString());
//					quantity--;
//					quantityTxt.setText(quantity+"");
//				}
//
//			}
//		});
//		final Button addButton = (Button) findViewById(R.id.addButton);
//		addButton.setOnClickListener(new View.OnClickListener() {
//
//			@SuppressLint("NewApi")
//			@Override
//			public void onClick(View v) {
//				boolean checkPrice = itemPrice.getText().toString().isEmpty();
//				boolean checkBarcode = itemBarcode.getText().toString().isEmpty();
//				if(checkPrice||checkBarcode){
//					Toast.makeText(StockAddActivity.this,
//							"Still have some Blank Field!", Toast.LENGTH_SHORT)
//							.show();
//				}
//				else{
//					Date now = new Date();
//					String time = new SimpleDateFormat("dd MMM yyyy").format(now);
//					int id = inventory.getProductCatalog().getProductByBarcode(itemBarcode.getText().toString()).getId();
//					boolean success = inventory.getStock().addProductLot(time, quantity, id,Double.parseDouble(itemPrice.getText().toString()));
//					if(success){
//						Toast.makeText(StockAddActivity.this,
//								"Successfully Add : "+itemName.getText().toString(), Toast.LENGTH_SHORT)
//								.show();
//					}
//					else{
//						Toast.makeText(StockAddActivity.this,
//								"FAIL to Add : "+itemName.getText().toString(), Toast.LENGTH_SHORT)
//								.show();
//					}
//				}
//			}
//		});
//
//		final Button clearButton = (Button) findViewById(R.id.clearButton);
//		clearButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				quantity=0;
//				itemBarcode.setText("");
//				itemName.setText("");
//				itemPrice.setText("");
//				quantityTxt.setText(quantity+"");
//
//			}
//		});
//	}
//
//}
