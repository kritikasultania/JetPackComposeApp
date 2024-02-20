package com.example.jetpackcomposeexample

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun LoginScreen(openDashboard: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            ConstraintLayout {
                val (loginForm) = createRefs()
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .constrainAs(loginForm) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp)
                    ) {
                        HeaderView()
                        val loginText = "Log in to your account."
                        val loginWord = "Log in"
                        val loginAnnotatedString = buildAnnotatedString {
                            append(loginText)
                            addStyle(
                                style = SpanStyle(
                                    color = dark_gray,
                                ),
                                start = 0,
                                end = loginText.length
                            )
                            addStyle(
                                style = SpanStyle(
                                    color = colorPrimary,
                                ),
                                start = 0,
                                end = loginWord.length
                            )
                        }

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 20.dp),
                            text = loginAnnotatedString,
                            textAlign = TextAlign.Center,
                            fontSize = 22.sp,
                        )
                        Text(
                            text = "Email Address",
                            style = MaterialTheme.typography.subtitle1.copy(color = gray),
                            modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                        )

                        OutlinedTextField(
                            value = email,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White),
                            onValueChange = { valueChanged ->
                                email = valueChanged
                                emailError = !isValidEmail(valueChanged)
                            },
                            shape = RoundedCornerShape(10.dp),
                            isError = emailError,
                            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            placeholder = { Text(text = "Email Address") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = colorPrimary,
                                focusedLabelColor = Color.White,
                                trailingIconColor = Color.White,
                            ),
                            visualTransformation = VisualTransformation.None
                        )

                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.subtitle1.copy(color = gray),
                            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                        )

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White),
                            value = password,
                            onValueChange = { valueChanged ->
                                password = valueChanged
                                passwordError = !isValidPassword(valueChanged)
                            },
                            shape = RoundedCornerShape(10.dp),
                            isError = passwordError,
                            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            placeholder = { Text(text = "Password") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = colorPrimary,
                                focusedLabelColor = Color.White,
                                trailingIconColor = Color.White,
                            ),
                            visualTransformation = PasswordVisualTransformation()
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            text = "Forgot Password?",
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.subtitle2.copy(color = colorPrimary)
                        )
                        val context = LocalContext.current
                        Button(
                            onClick = {
                              if (isValidEmail(email) && isValidPassword(password)) {
                                    openDashboard()
                                } else {
                                    Toast.makeText(context, "Failed to Login", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            modifier = Modifier
                                .padding(top = 30.dp, bottom = 34.dp)
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                                text = "Login",
                                color = Color.White,
                                style = MaterialTheme.typography.button
                            )
                        }
                        val signInText = "Don't have an account? Sign In"
                        val signInWord = "Sign In"
                        val signInAnnotatedString = buildAnnotatedString {
                            append(signInText)
                            addStyle(
                                style = SpanStyle(
                                    color = light_gray,
                                ),
                                start = 0,
                                end = signInText.length
                            )
                            addStyle(
                                style = SpanStyle(
                                    color = colorPrimary,
                                ),
                                start = signInText.indexOf(signInWord),
                                end = signInText.length
                            )
                        }

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = signInAnnotatedString,
                            style = TextStyle(
                                fontSize = 14.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreen(openDashboard = {})
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPassword(password: String): Boolean {
    return password.length >= 6
}

@Composable
fun HeaderView() {
    Image(
        modifier = Modifier.fillMaxSize(),
        bitmap = ImageBitmap.imageResource(id = R.drawable.img_login_screen_banner),
        contentScale = ContentScale.FillWidth,
        contentDescription = "header_view_login_bg"
    )
}
