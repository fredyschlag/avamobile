package br.furb.avamobile.activities;

import java.util.List;

import br.furb.avamobile.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends BaseExpandableListAdapter {
	private List<DadosFinanceiros> dados;
    private LayoutInflater inflater;

    public Adapter(Context context, List<DadosFinanceiros> dados) {
        this.dados = dados;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return dados.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dados.get(groupPosition).getDados().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dados.get(groupPosition).getName();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dados.get(groupPosition).getDados().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
    	DadosFinanceiros item = dados.get(groupPosition);
    	
    	if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, parent, false);
        }

    	((TextView) convertView.findViewById(R.id.text)).setText(item.getName());
        //TODO: pegar o objeto verificar se esta pago ou não
        ((ImageView) convertView.findViewById(R.id.imagemview)).setImageResource(item.getIconeRID());
        
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.text)).setText(getChild(groupPosition,childPosition).toString());
        
//        ((TextView)convertView).setText(getChild(groupPosition,childPosition).toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}
