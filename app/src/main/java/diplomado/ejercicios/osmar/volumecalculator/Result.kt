package diplomado.ejercicios.osmar.volumecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import diplomado.ejercicios.osmar.volumecalculator.databinding.ActivityResultBinding
import kotlin.math.sqrt

class Result : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private var equationReceived: Int = 0

    private val pi: Double = 3.141592

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if(bundle!=null) {
            binding.txtViewResultValue.setText("")
            binding.txtViewTitleResult.setText(R.string.resultVolume)
            equationReceived = bundle.getInt("equationOpt",0)
            when (equationReceived) {
                1 -> {
                    binding.imgViewEquationResult.setImageResource(R.drawable.cuboid_eq)
                    calculateCuboidVolume(
                        bundle.getInt("a", 0),
                        bundle.getInt("b", 0),
                        bundle.getInt("h", 0)
                    )
                }
                2 -> {
                    binding.imgViewEquationResult.setImageResource(R.drawable.cone_eq)
                    calculateConeVolume(
                        bundle.getInt("r", 0),
                        bundle.getInt("h", 0)
                    )
                }
                3 -> {
                    binding.imgViewEquationResult.setImageResource(R.drawable.cylinder_eq)
                    calculateCylinderVolume(
                        bundle.getInt("r", 0),
                        bundle.getInt("h", 0)
                    )
                }
                4 -> {
                    binding.imgViewEquationResult.setImageResource(R.drawable.tetrahedron_reg_eq)
                    calculateTetraRegVolume(
                        bundle.getInt("a", 0)
                    )
                }
                else -> {
                    equationReceived = bundle.getInt("equationOpt", 0)
                }
            }
        }else {
            Toast.makeText(this, R.string.errorEmptySpace, Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateCuboidVolume(baseLength: Int, baseWidth: Int, height: Int){
        binding.txtViewTitleResult.setText(resources.getString(R.string.resultVolume, resources.getString(R.string.cuboid)))
        val volume: Int = baseLength * baseWidth * height
        binding.txtViewResultValue.setText(volume.toString())
    }

    private fun calculateConeVolume(radius: Int, height: Int){
        binding.txtViewTitleResult.setText(resources.getString(R.string.resultVolume, resources.getString(R.string.cone)))
        val volume: Double = (pi * radius * radius * height)/3
        binding.txtViewResultValue.setText(volume.toString())
    }

    private fun calculateCylinderVolume(radius: Int, height: Int){
        binding.txtViewTitleResult.setText(resources.getString(R.string.resultVolume, resources.getString(R.string.cylinder)))
        val volume: Double = pi * (radius * radius) * height
        binding.txtViewResultValue.setText(volume.toString())
    }

    private fun calculateTetraRegVolume(edgeLength: Int){
        binding.txtViewTitleResult.setText(resources.getString(R.string.resultVolume, resources.getString(R.string.tetra)))
        val volume: Double =  ((edgeLength * edgeLength * edgeLength) * sqrt(2.0))/12
        binding.txtViewResultValue.setText(volume.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}