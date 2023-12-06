import { createApp } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'
const app = Vue.createApp({
  data() {
    return {
      displayValue: '0',
    }
  },
  methods: {
    appendNumber(number) {
      if (this.displayValue == '0') {
        this.displayValue = this.displayValue.slice(0, -1);
        this.displayValue += number;
      }
      else
        this.displayValue += number;
    },
    appendOperator(operator) {
      if (operator == '-')
      {
        if (this.displayValue[-1] != '-')
          this.displayValue += operator;
      }
      else if(operator != '-') {
        if (!this.displayValue.includes('+') && !this.displayValue.includes('*') && !this.displayValue.includes('%') && !this.displayValue.includes('÷') && !this.displayValue.includes('√') && !this.displayValue.includes('²') && !this.displayValue.includes('⁻¹'))
              this.displayValue += operator;
      }
    },
    appendDecimal() {
        if (!this.displayValue.includes('.'))
        this.displayValue += '.';
    },
    clear(){
        this.displayValue = '';
    },
    deleteLastChar() {
      if (this.displayValue == 'Error')
        this.displayValue = '';
      else
        this.displayValue = this.displayValue.slice(0, -1);
    },
    reverse() {
      if (this.displayValue[0] == '-')
        this.displayValue = this.displayValue.slice(1, 2);
      else
        this.displayValue = '-' + this.displayValue;
    },
    calculate() {
      fetch('http://localhost:8080/calculate', {
        method: 'POST',
        body: JSON.stringify(this.displayValue),
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then((res) => {
        return res.text();
      })
      .then((data) => {
        console.log(data);
        if(this.displayValue != "")
            this.displayValue = data
      })
      .catch((error) => {
        console.log("Error");
      });
    }
  }      
})
app.mount('#app')
