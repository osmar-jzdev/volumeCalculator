package diplomado.ejercicios.osmar.volumecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import diplomado.ejercicios.osmar.volumecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    private lateinit var binding: ActivityMainBinding

    private var equationSelected: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner: Spinner = binding.equationsSpinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.equations_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this

    }

    /**
     * This method perform the following actions when a user select some option from the spinner:
     *    Depends on the equation selected by the user from the spinner this method display a set
     *    of variables inside a card view, and display the image related to the selected equation
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        /* Debug to track which option was selected by the user from the spinner
        val equationName: String = parent?.getItemAtPosition(position).toString()
        Toast.makeText(this@MainActivity, "Equation selected: $equationName", Toast.LENGTH_SHORT).show()*/
        resetTextInputArea()
        when(position){
            1 ->{
                binding.imgViewEquation.setImageResource(R.drawable.cuboid_eq)
                binding.inputLayoutValueVar.setHint(R.string.hintVarA)
                binding.inputLayoutValueVar2.isVisible = true
                binding.inputLayoutValueVar2.setHint(R.string.hintVarB)
                binding.inputLayoutValueVar3.isVisible = true
                binding.inputLayoutValueVar3.setHint(R.string.hintVarH)
                equationSelected = 1
            }
            2 -> {
                binding.imgViewEquation.setImageResource(R.drawable.cone_eq)
                binding.inputLayoutValueVar.setHint(R.string.hintVarR)
                binding.inputLayoutValueVar2.isVisible = true
                binding.inputLayoutValueVar2.setHint(R.string.hintVarH)
                binding.inputLayoutValueVar3.isVisible = false
                equationSelected = 2
            }
            3 -> {
                binding.imgViewEquation.setImageResource(R.drawable.cylinder_eq)
                binding.inputLayoutValueVar.setHint(R.string.hintVarR)
                binding.inputLayoutValueVar2.setHint(R.string.hintVarH)
                binding.inputLayoutValueVar3.isVisible = false
                equationSelected = 3
            }
            4 -> {
                binding.imgViewEquation.setImageResource(R.drawable.tetrahedron_reg_eq)
                binding.inputLayoutValueVar.setHint(R.string.hintVarTetra)
                binding.inputLayoutValueVar2.isVisible = false
                binding.inputLayoutValueVar3.isVisible = false
                equationSelected = 4
            }
            else -> {
                binding.imgViewEquation.setImageResource(R.drawable.gummy_blackboard_splash)
                equationSelected = 0
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun resetTextInputArea(){
        binding.inputEdTextVarValue.error = null
        binding.inputLayoutValueVar.isErrorEnabled = false
        binding.inputEdTextVarValue2.error = null
        binding.inputLayoutValueVar2.isErrorEnabled = false
        binding.inputEdTextVarValue3.error = null
        binding.inputLayoutValueVar3.isErrorEnabled = false
        binding.inputEdTextVarValue.setText("")
        binding.inputLayoutValueVar.setHint(R.string.hintVariableTextBox)
        binding.inputEdTextVarValue2.setText("")
        binding.inputLayoutValueVar2.setHint(R.string.hintVariableTextBox)
        binding.inputEdTextVarValue3.setText("")
        binding.inputLayoutValueVar3.setHint(R.string.hintVariableTextBox)
    }

    /**
     * Method that is called when the user hit the button calculate and validate if an input text box
     * has some input value or is empty.
     *
     * Return boolean if a box is visible and has an empty value
     */
    private fun validateEmptyVariable(): Boolean {
        var emptyVarFlag: Boolean =  false
        with(binding){
            if(inputLayoutValueVar.isVisible &&inputEdTextVarValue.text.toString().isEmpty()) {
                inputEdTextVarValue.error = resources.getString(R.string.errorVariableCanNotBeNull)
                Toast.makeText(this@MainActivity, R.string.errorEmptySpace, Toast.LENGTH_SHORT).show()
                emptyVarFlag = true
            }
            if(inputLayoutValueVar2.isVisible && inputEdTextVarValue2.text.toString().isEmpty()) {
                inputEdTextVarValue2.error = resources.getString(R.string.errorVariableCanNotBeNull)
                Toast.makeText(this@MainActivity, R.string.errorEmptySpace, Toast.LENGTH_SHORT).show()
                emptyVarFlag = true
            }
            if(inputLayoutValueVar3.isVisible && inputEdTextVarValue3.text.toString().isEmpty()) {
                inputEdTextVarValue3.error = resources.getString(R.string.errorVariableCanNotBeNull)
                Toast.makeText(this@MainActivity, R.string.errorEmptySpace, Toast.LENGTH_SHORT).show()
                emptyVarFlag = true
            }
        }
        return emptyVarFlag
    }

    /**
     * Based on the equation selected and if any input text field is empty this method passed the input
     * data to next activity when the user hit the calculate button.
     * The passed data is the value of the variables and the equation image.
     */
    fun clickCalculate(view: View) {
        when(equationSelected){
            1 -> {
                if(!validateEmptyVariable()){
                    val intent =  Intent(this@MainActivity, Result::class.java)
                    intent.putExtra("equationOpt", 1)
                    intent.putExtra("a", binding.inputEdTextVarValue.text.toString().toInt())
                    intent.putExtra("b", binding.inputEdTextVarValue2.text.toString().toInt())
                    intent.putExtra("h", binding.inputEdTextVarValue3.text.toString().toInt())
                    startActivity(intent)
                    finish()
                }
            }
            2 -> {
                if(!validateEmptyVariable()){
                    val intent =  Intent(this@MainActivity, Result::class.java)
                    intent.putExtra("equationOpt", 2)
                    intent.putExtra("r", binding.inputEdTextVarValue.text.toString().toInt())
                    intent.putExtra("h", binding.inputEdTextVarValue2.text.toString().toInt())
                    startActivity(intent)
                    finish()
                }
            }
            3 -> {
                if(!validateEmptyVariable()){
                    val intent =  Intent(this@MainActivity, Result::class.java)
                    intent.putExtra("equationOpt", 3)
                    intent.putExtra("r", binding.inputEdTextVarValue.text.toString().toInt())
                    intent.putExtra("h", binding.inputEdTextVarValue2.text.toString().toInt())
                    startActivity(intent)
                    finish()
                }
            }
            4 -> {
                if(!validateEmptyVariable()){
                    val intent =  Intent(this@MainActivity, Result::class.java)
                    intent.putExtra("equationOpt", 4)
                    intent.putExtra("a", binding.inputEdTextVarValue.text.toString().toInt())
                    startActivity(intent)
                    finish()
                }
            }
            else -> {
                Toast.makeText(this, R.string.noEquationSelected, Toast.LENGTH_SHORT).show()
            }
        }
    }
}