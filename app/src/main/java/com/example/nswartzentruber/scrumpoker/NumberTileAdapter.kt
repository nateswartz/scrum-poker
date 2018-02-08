
import android.R.attr.name
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import android.R.attr.name

class UsersAdapter(context: Context, users: ArrayList<User>) : ArrayAdapter<User>(context, 0, users) {

    fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        // Get the data item for this position
        val user = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        }
        // Lookup view for data population
        val tvName = convertView!!.findViewById(R.id.tvName) as TextView
        val tvHome = convertView!!.findViewById(R.id.tvHome) as TextView
        // Populate the data into the template view using the data object
        tvName.setText(user!!.name)
        tvHome.setText(user!!.hometown)
        // Return the completed view to render on screen
        return convertView
    }
}