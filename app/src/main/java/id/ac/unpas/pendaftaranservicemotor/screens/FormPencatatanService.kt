package id.ac.unpas.pendaftaranservicemotor.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.launch
import id.ac.unpas.pendaftaranservicemotor.model.ServiceMotor
import id.ac.unpas.pendaftaranservicemotor.persistences.ServiceMotorDao
import id.ac.unpas.pendaftaranservicemotor.ui.theme.Purple700
import id.ac.unpas.pendaftaranservicemotor.ui.theme.Teal200

@Composable
fun FormPencatatanService(serviceMotorDao: ServiceMotorDao) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val nopolisi = remember { mutableStateOf(TextFieldValue("")) }
    val tanggal = remember { mutableStateOf(TextFieldValue("")) }
    val kilometer = remember { mutableStateOf(TextFieldValue("")) }
    val kata = "Pendaftaran Service Motor"

    println(kata)
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        Text(text = "Pendaftaran Service Motor", style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
            ,modifier = Modifier.padding(2.dp))
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "isi disini") }
        )
        OutlinedTextField(
            label = { Text(text = "Nomor Polisi") },
            value = nopolisi.value,
            onValueChange = {
                nopolisi.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "D XXX XXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal") },
            value = tanggal.value,
            onValueChange = {
                tanggal.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "DDD-MMM-YYYY") }
        )
        OutlinedTextField(
            label = { Text(text = "Kilometer") },
            value = kilometer.value,
            onValueChange = {
                kilometer.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "100000") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {

                if(nama.value.text !== "" && nopolisi.value.text !== ""){
                    if (tanggal.value.text !== "" && kilometer.value.text !== "" ){
                        val id = uuid4().toString()
                        val item = ServiceMotor(id, nama.value.text, nopolisi.value.text,
                            tanggal.value.text, kilometer.value.text)
                        scope.launch {
                            serviceMotorDao.insertAll(item)
                        }
                        nama.value = TextFieldValue("")
                        nopolisi.value = TextFieldValue("")
                        tanggal.value = TextFieldValue("")
                        kilometer.value = TextFieldValue("")
                    }else{
                        Toast.makeText(context, "Harap Di isi dengan lengkap",
                            Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(context, "Harap Di isi dengan lengkap",
                        Toast.LENGTH_LONG).show()
                }



            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                nama.value = TextFieldValue("")
                nopolisi.value = TextFieldValue("")
                tanggal.value = TextFieldValue("")
                kilometer.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}