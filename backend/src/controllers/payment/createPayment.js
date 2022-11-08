const Payment = require("../../models/Payment");
const bcrypt = require("bcryptjs");
const Card = require("../../models/Card");
const dotenv = require("dotenv");

dotenv.config();

// Create payment
const createPayment = async(req, res) => {
    // Get payload
    const userID = req.user.id;
    // Read card
    const card = await Card.findOne({ 
        where: { UserID: userID }
    });
    if (!card) return res.status(404).send(`Card with UserID: ${userID} is not found`);    

    // Proceed to payment
    if (req.body.pinNumber) {
        // Checking if pin number is correct
        const validPinNumber = await bcrypt.compare(req.body.pinNumber, card.pinNumber);
        if (!validPinNumber) return res.status(400).send("Invalid pin number!");
        // Update saldo on card
        const updatedSaldo = card.saldo - req.body.amount;
        if (updatedSaldo < 0) return res.status(200).send(`Card saldo with CardID: ${card.id} is not enough`);
        card.set({ 
            saldo: updatedSaldo
        });
        // Create payment
        const payment = await Payment.create({
            CardID: card.id,
            amount: req.body.amount,
            foodcourtName: req.body.foodcourtName,
            merchantName: req.body.merchantName,
            date: new Date().toLocaleDateString()
        });
        await payment.save();
        await card.save();
        return res.json({ payment, card });
    } else {
        return res.status(500).send("Pin number is empty");
    }
}

module.exports = createPayment;