let isNumOfDays = false;
const receiptList = document.getElementById("receiptList");
const dateList = document.getElementById("dateList");
const numOfDaysCheckbox = document.getElementById("numOfDaysCB");
document.getElementById('tripDate').valueAsDate = new Date();

receiptList.addEventListener('click', function (event) {
    if (event.target.tagName == 'BUTTON') {
        this.removeChild(event.target.parentElement);
    }
});


numOfDaysCheckbox.addEventListener('change', function () {
    isNumOfDays = this.checked ? true : false;
    displayDate();
});


dateList.addEventListener('click', function (event) {
    if (event.target.tagName == 'BUTTON') {
        this.removeChild(event.target.parentElement);
    }
});


const optionsList = document.querySelector('.options');
optionsList.addEventListener('click', function (event) {
    if (event.target.tagName === 'LI') {
        const selectedValue = event.target.getAttribute('data-value');
        addReceipt(selectedValue);
    }
});

function addReceipt(value) {
    const receiptList = document.getElementById("receiptList");
    const receipt = document.createElement('li');
    receipt.innerHTML = `<input readonly class="item" name="receipt" type="text" value="${value}">
    <button class="button delete-button" type="button">delete</button>`;
    receiptList.appendChild(receipt);
}

function addTimeframe() {
    const targetTimeframeInput = document.getElementById("targetTimeframe");
    let value = 0;
    if (isNumOfDays) {
        const numOfDaysInput = document.getElementById('numOfDaysInput');
        value = numOfDaysInput.value + ' day(s)';
    } else {
        const startDateInput = document.getElementById('startDate');
        const endDateInput = document.getElementById('endDate');

        value = `From ${startDateInput.value} to ${endDateInput.value}`;
    }

    targetTimeframeInput.value = value;
}


function addExcludedDate() {
    const dateList = document.getElementById("dateList");
    const date = document.createElement('li');
    const excludedDateInput = document.getElementById('excludedDate');
    const value = 'Excluded: ' + excludedDateInput.value;
    date.innerHTML = `<input readonly class="item" name="excludedDates" type="text" value="${value}">
    <button class="button delete-button" type="button">delete</button>`;
    dateList.appendChild(date);
}

function displayDate() {
    const numOfDaysInput = document.getElementById('numOfDaysInput');
    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');

    if (isNumOfDays) {
        numOfDaysInput.classList.add('visible');
        numOfDaysInput.classList.remove('invisible');

        startDateInput.classList.add('invisible');
        startDateInput.classList.remove('visible');
        endDateInput.classList.add('invisible');
        endDateInput.classList.remove('visible');
    } else {
        numOfDaysInput.classList.add('invisible');
        numOfDaysInput.classList.remove('visible');

        startDateInput.classList.add('visible');
        startDateInput.classList.remove('invisible');
        endDateInput.classList.add('visible');
        endDateInput.classList.remove('invisible');
    }
}


