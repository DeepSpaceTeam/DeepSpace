package ru.myitschool.nasa_bootcamp.ui.spacex.explore.dragons

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import ru.myitschool.nasa_bootcamp.data.model.DragonModel
import ru.myitschool.nasa_bootcamp.data.model.RoverModel
import ru.myitschool.nasa_bootcamp.databinding.DragonItemBinding

import java.util.ArrayList

class DragonsAdapter internal constructor(
    context: Context,
    dragonModels: ArrayList<DragonModel>,
   // val navController: NavController
) :
    RecyclerView.Adapter<DragonsViewHolder>() {
    var context: Context
    var dragonModels: ArrayList<DragonModel>

    internal interface onDragonClick {
        fun onDragonClick(roverModel: RoverModel, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragonsViewHolder {
        return DragonsViewHolder(
            DragonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            context
        )
    }

    override fun onBindViewHolder(holder: DragonsViewHolder, position: Int) {
        val dragonModel: DragonModel = dragonModels[position]

        holder.binding.activeDragon.text = "Is active? ${if (dragonModel.active) "Yes" else "No"}"

        holder.binding.descriptionDragon.text = "${dragonModel.description}"

        holder.binding.landPayloadMassDragon.text =
            "Land Payload Mass : ${dragonModel.return_payload_mass.kg}"

        holder.binding.launchPayloadMassDragon.text =
            "Launch Payload Mass : ${dragonModel.launch_payload_mass.kg}"

        holder.binding.massKgDragon.text = "Mass (kg) : ${dragonModel.dry_mass_kg}"

        holder.binding.nameDragon.text = "${dragonModel.name}"

        holder.binding.typeDragon.text = "Type: ${dragonModel.type}"

        holder.binding.orbitDurationDragon.text = "Orbit duration: ${dragonModel.orbit_duration_yr}"

        holder.binding.typeThruster.text = "Type: ${dragonModel.thrusters[0].type}"
        holder.binding.amountThruster.text = "Amount: ${dragonModel.thrusters[0].amount}"
        holder.binding.fuel1Thruster.text = "Fuel 1: ${dragonModel.thrusters[0].fuel_1}"
        holder.binding.fuel2Thruster.text = "Fuel 2: ${dragonModel.thrusters[0].fuel_2}"
        holder.binding.pods.text = "Pods: ${dragonModel.thrusters[0].pods}"

        val onDragonClickListener = object : onDragonClick {
            override fun onDragonClick(roverModel: RoverModel, position: Int) {

            }
        }

    }

    override fun getItemCount(): Int {
        return dragonModels.size
    }

    fun update(modelList: ArrayList<DragonModel>) {
        dragonModels = modelList
        notifyDataSetChanged()
    }

    init {
        this.context = context
        this.dragonModels = dragonModels
    }
}