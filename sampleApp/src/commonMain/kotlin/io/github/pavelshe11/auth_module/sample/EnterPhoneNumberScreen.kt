package io.github.pavelshe11.auth_module.sample

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.pavelshe11.auth_module.sample.views.ShellButton
import io.github.pavelshe11.auth_module.sample.views.ShellOutlinedTextField
import io.github.pavelshe11.auth_module.sampleapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    country: TextFieldValue,
    countryInputOnValueChange: (TextFieldValue) -> Unit,
    phone: TextFieldValue,
    phoneInputOnValueChange: (TextFieldValue) -> Unit,
    loginOnClick: () -> Unit,
) {
    Box {
        backgroundLoginScreen(Modifier.fillMaxSize().background(MaterialTheme.colors.background))
        LoginLayout(country, countryInputOnValueChange, phone, phoneInputOnValueChange, loginOnClick)
    }
}

private val colorText = Color(0xFF6C7278)

@Composable
private fun LoginLayout(
    country: TextFieldValue,
    countryInputOnValueChange: (TextFieldValue) -> Unit,
    phone: TextFieldValue,
    phoneInputOnValueChange: (TextFieldValue) -> Unit,
    loginOnClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current


    val titleBrush = Brush.horizontalGradient(
        colorStops = arrayOf(
            0.0f to Color(0xFF4983F6),
            0.5f to Color(0xFFC175F5),
            1f to Color(0xFFFBACB7)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .height(50.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(painterResource(Res.drawable.app_icon), "", contentScale = ContentScale.Fit)
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 12.dp),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                stringResource(Res.string.app_name),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 18.sp
                            )

                            Text(
                                "by ShellDev",
                                color = Color.Black,
                                style = TextStyle(
                                    fontSize = 8.sp,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 8.sp,
                                )
                            )
                        }
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        },
        backgroundColor = Color.Transparent,
        contentColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter),
                shape = RoundedCornerShape(10.dp),
                elevation = 0.dp,
                backgroundColor = Color(0x99FFFFFF),
                border = BorderStroke(1.dp, Color.White),
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp)
                        .imePadding()
                        .verticalScroll(scrollState)
                ) {
                    Text(
                        text = stringResource(Res.string.login_screen_title),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp,
                            brush = titleBrush
                        ),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 12.dp),
                    )

                    Text(
                        text = stringResource(Res.string.login_screen_subtitle),
                        color = colorText,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                    )
                    LoginTextField(
                        country,
                        countryInputOnValueChange,
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                        ),
                        label = {
                            Text("Страна", color = colorText, fontSize = 12.sp)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LoginTextField(
                        phone,
                        phoneInputOnValueChange,
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                        ),
                        label = {
                            Text("Телефон", color = colorText, fontSize = 12.sp)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                    )
                    ShellButton(
                        onClick = loginOnClick,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(stringResource(Res.string.login_screen_button_text))
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painterResource(Res.drawable.arrow_narrow_left),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp).rotate(180f)
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}

@Composable
private fun LoginTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    Column(modifier) {
        label?.invoke()
        Spacer(Modifier.height(4.dp))
        ShellOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = textStyle,
            shape = RoundedCornerShape(10.dp),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            placeholder = {},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                unfocusedBorderColor = Color(0xFFEDF1F3)
            )
        )
    }
}

@Composable
private fun backgroundLoginScreen(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val radius = size.width * 0.86f
        val tlhBrush = Brush.radialGradient(listOf(Color(0xFF94BCEB), Color.Transparent), radius = radius)
        val tlhOffset = -size.width / 2
        val tlvOffset = -350.dp.toPx() + 60.dp.toPx()

        translate(tlhOffset, tlvOffset) {
            drawCircle(brush = tlhBrush, radius = radius)
        }

        val brhBrush = Brush.radialGradient(listOf(Color(0xFFB49EF4), Color.Transparent), radius = radius)
        val brhOffset = size.width / 2 + size.width / 5f
        val brvOffset = -size.height / 2 + size.height / 3f + 60.dp.toPx()

        translate(brhOffset, brvOffset) {
            drawCircle(brhBrush, radius = radius)
        }
    }
}