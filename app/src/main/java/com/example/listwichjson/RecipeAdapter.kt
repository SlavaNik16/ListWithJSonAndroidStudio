package com.example.listwichjson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.listwichjson.databinding.ListItemRecipeBinding
import com.squareup.picasso.Picasso

class RecipeAdapter (
    private val context:Context,
    private val dataSource:ArrayList<Recipe>
):BaseAdapter() {
    private val inflater:LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View
        val holder:ViewHolder

        if(convertView == null){
            view = inflater.inflate(R.layout.list_item_recipe,parent,false)
            var binding  = ListItemRecipeBinding.bind(view)
            holder = ViewHolder()
            holder.Img = binding.imageView
            holder.txtTitle = binding.recipeListTitle
            holder.txtSubtitle = binding.recipeListSubtitle
            holder.txtDetail = binding.recipeListDetail

            view.tag = holder
        }else{
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val recipe = getItem(position) as Recipe
        holder.txtTitle.text = recipe.title
        holder.txtSubtitle.text = recipe.description
        holder.txtDetail.text = recipe.label
        Picasso.with(context)
            .load(recipe.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.Img)

        holder.txtTitle.typeface = ResourcesCompat.getFont(context,R.font.josefinsans_bold)
        holder.txtSubtitle.typeface = ResourcesCompat.getFont(context,R.font.josefinsans_semibolditalic)
        holder.txtDetail.typeface = ResourcesCompat.getFont(context,R.font.quicksand_bold)

        holder.txtDetail.setTextColor(
            ContextCompat.getColor(
                context, LABEL_COLORS[recipe.label] ?:R.color.colorPrimary
            )
        )
        return view
    }
    class ViewHolder {
        lateinit var Img: ImageView
        lateinit var txtTitle: TextView
        lateinit var txtSubtitle: TextView
        lateinit var txtDetail: TextView
    }

    companion object{
        private val LABEL_COLORS = hashMapOf(
            "Low-Carb" to R.color.colorLowCarb,
            "Low-Fat" to R.color.colorLowFat,
            "Low-Sodium" to R.color.colorLowSodium,
            "Medium-Carb" to R.color.colorMediumCarb,
            "Vegetarian" to R.color.colorVegetarian,
            "Balanced" to R.color.colorBalanced,
        )
    }

}