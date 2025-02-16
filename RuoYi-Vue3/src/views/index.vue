<template>
  <div class="app-container home">
    <div style="display: flex;">
      <div style="flex: 1; margin-top: 20px; margin-left: 10px;">
        <div id="Line" style="width: 100%; height: 400px;"></div>
      </div>

      <div style="flex: 1; margin-top: 62px;">
        <div id="bar" style="width: 100%; height: 400px;"></div>
      </div>
    </div>
    <div style="display: flex;">
      <div style="flex: 1; margin-top: 28px;">
        <div id="bie" style="width: 100%; height: 400px;"> </div>
        </div>
      <div style="flex: 1; margin-top: 28px;">
        <div id="dessert" style="width: 100%; height: 400px;"> </div>
        </div>
    </div>
  </div>
</template>


<script>
import * as echarts from 'echarts';
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.4",
    };
  },

  mounted() {
    this.initBie();
    this.initBar();
    this.initLine();
    this.initDessert();
  },

  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },

  

    //饼状图统计
    initBie() {
      var chartDom = document.getElementById('bie');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        title: {
          text: '咖啡销售量',
          subtext: '各类咖啡销售量',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Coffee',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: '猫屎咖啡' },
              { value: 735, name: '陨石新拿铁' },
              { value: 580, name: '冰美式' },
              { value: 484, name: '生椰拿铁' },
              { value: 300, name: '丝绒拿铁' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };

      option && myChart.setOption(option);
    },

    initDessert() {
      var chartDom = document.getElementById('dessert');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        title: {
          text: '甜品销售量',
          subtext: '各类甜品销售量',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Coffee',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: '提拉米苏' },
              { value: 735, name: '马卡龙' },
              { value: 580, name: '甜甜圈' },
              { value: 484, name: '千层蛋糕' },
              { value: 300, name: '曲奇饼干' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };

      option && myChart.setOption(option);
    },

    //柱状图统计
    initBar() {
      var chartDom = document.getElementById('bar');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        title: {
          text: '总数据',
          subtext: '各类总数据',
          left: 'center'
        },
        xAxis: {
          type: 'category',
          data: ['咖啡', '甜品', '猫咪', '人流', '其他']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'bar'
          }
        ]
      };

      option && myChart.setOption(option);
    },

    //折线图统计
    initLine() {
      var chartDom = document.getElementById('Line');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        title: {
          text: '猫咪主题咖啡馆总数据'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['咖啡', '猫咪', '人流', '甜品', '其他']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '咖啡',
            type: 'line',
            stack: 'Total',
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: '甜品',
            type: 'line',
            stack: 'Total',
            data: [320, 332, 301, 334, 390, 330, 320]
          },
          {
            name: '猫咪',
            type: 'line',
            stack: 'Total',
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: '人流',
            type: 'line',
            stack: 'Total',
            data: [150, 232, 201, 154, 190, 330, 410]
          },
          {
            name: '其他',
            type: 'line',
            stack: 'Total',
            data: [820, 932, 901, 934, 1290, 1330, 1320]
          }
        ]
      };

      option && myChart.setOption(option);
    },

  }
}


</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans",
  "Helvetica Neue",
  Helvetica,
  Arial,
  sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>
