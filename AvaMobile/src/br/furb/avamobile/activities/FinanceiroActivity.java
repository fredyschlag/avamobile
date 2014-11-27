package br.furb.avamobile.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import br.furb.avamobile.R;

public class FinanceiroActivity extends Activity {
    private static List<DadosFinanceiros> dados;
    private ExpandableListView expandableListView;
    private Adapter adapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financeiro);
        
        dados = (ArrayList<DadosFinanceiros>) getIntent().getSerializableExtra("dados");
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        adapter = new Adapter(this, dados);
        expandableListView.setAdapter(adapter);
    }

}
