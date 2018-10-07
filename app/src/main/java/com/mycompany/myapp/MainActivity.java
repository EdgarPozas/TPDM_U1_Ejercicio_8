package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.view.*;
import java.util.*;
import android.widget.*;
import android.text.*;
import android.content.*;
import android.widget.ActionMenuView.*;

public class MainActivity extends Activity 
{
	private ArrayList<EditText> txts;
	private LinearLayout padre;
	private EditText cantidad;
	private int can;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		txts=new ArrayList<EditText>();
		padre=findViewById(R.id.padre);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		
		getMenuInflater().inflate(R.menu.menu,menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.asignar:
				AlertDialog.Builder alerta=new AlertDialog.Builder(this);
				View la=getLayoutInflater().inflate(R.layout.pedir,null);
				alerta.setView(la);
				cantidad=la.findViewById(R.id.cantidad);
				alerta.setPositiveButton("Aceptar",new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface d,int l){
							if(cantidad.getText().toString().isEmpty()){
								Toast.makeText(MainActivity.this,"Llene los campos",Toast.LENGTH_SHORT).show();
								return;
							}
							can=Integer.parseInt(cantidad.getText().toString());
							Toast.makeText(MainActivity.this,"Seleccionados "+cantidad.getText().toString(),Toast.LENGTH_SHORT).show();
							//int can=Integer.parseInt(cantidad.getText().toString());
							//creartxt(can);
						}
					});
				alerta.show();
				return true;
			case R.id.generar:
				creartxt(can);
				return true;
			case R.id.calcular:
				int suma=0;
				for(EditText ed :txts){
					if(ed.getText().toString().isEmpty()){
						ed.setText("0");
					}
					suma+=Integer.parseInt(ed.getText().toString());
				}
				Toast.makeText(this,"La suma es: "+suma,Toast.LENGTH_SHORT).show();
				return true;
			case R.id.salir:
				finish();
				return true;
			case R.id.limpiar:
				creartxt(0);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	private void creartxt(int num){
		padre.removeAllViews();
		txts.clear();
		for(int i=0;i<num;i++){
			EditText e=new EditText(this);
			e.setInputType(InputType.TYPE_CLASS_NUMBER);
			e.setHint("Escriba el valor "+i);
			padre.addView(e);
			txts.add(e);
		}
	}
}
