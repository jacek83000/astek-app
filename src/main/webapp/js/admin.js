const receiptList = document.getElementById("receiptList");

receiptList.addEventListener('click', function (event) {
    if (event.target.tagName == 'BUTTON') {
        this.removeChild(event.target.parentElement);
    }
});

function addReceipt() {
    const receiptList = document.getElementById("receiptList");

    const receipt = document.createElement('li');

    receipt.innerHTML = `<input class="item" name="receiptName" value="" type="text" placeholder="name">
    <input class="item" name="receiptPrice" type="number" value="" placeholder="price">
    <input class="item" name="receiptTypeLimit" type="number" value="" placeholder="type limit">
    <button class="button li-button delete-button" type="button">delete</button>`

    receiptList.appendChild(receipt);
}