package com.example.designsystem.chart

import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.designsystem.theme.KeypickComposeTheme
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun KeyPickChart(
    modifier: Modifier =Modifier,
    visitors : List<Float>,
    labels : List<String>
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LineChart(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setTouchEnabled(false) // 대시보드니까 터치 비활성화
                description.isEnabled = false
                legend.isEnabled = false

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    valueFormatter = IndexAxisValueFormatter(labels)
                    textColor = Color.Black.toArgb()
                    granularity = 1f                // x 값 간격을 1로 설정
                    labelCount = labels.size        // 라벨 개수 강제
                    isGranularityEnabled = true
                }

                axisLeft.apply {
                    axisMinimum = 0f
                    axisMaximum = (visitors.maxOrNull() ?: 100f)
                    textColor = Color.Black.toArgb()
                    setDrawGridLines(true)
                    gridColor = Color.Black.toArgb()
                    setLabelCount(3, true)
                }
                axisRight.isEnabled = false
            }
        },
        update = { chart ->
            val entries = visitors.mapIndexed { index, value ->
                Entry(index.toFloat(), value)
            }
            val dataSet = LineDataSet(entries, "Visitor").apply {
                color = Color(0xFF3B82F6).toArgb()
                setDrawValues(false)
                lineWidth = 2f
                setDrawCircles(true)
                circleRadius = 4f
                setCircleColor(Color(0xFF3B82F6).toArgb())

                setDrawFilled(true)
                fillDrawable = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(
                        Color(0xFF3B82F6).copy(alpha = 0.4f).toArgb(),
                        Color.Transparent.toArgb()
                    )
                )
            }
            chart.data = LineData(dataSet)
            chart.invalidate()
        }
    )

}
@Preview(showBackground = true)
@Composable
fun VisitorLineChartPreview() {
    KeypickComposeTheme{
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(
                "최근 5일 방문자 분석",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            KeyPickChart(
                visitors = listOf(950f, 800f, 1100f, 1200f, 1300f),
                labels = listOf("4일 전", "3일 전", "2일 전", "1일 전", "오늘")
            )
        }
    }
}