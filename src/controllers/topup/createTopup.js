const TopUp = require("../../models/TopUp");
const Card = require("../../models/Card");
const dotenv = require("dotenv");

dotenv.config();

const createTopUp = async (req, res) => {
    // Get payload
    const userID = req.user.id;
    // Read card
    const card = await Card.findOne({ 
        where: { UserID: userID }
    });
    if (!card) return res.status(404).send(`Card with UserID: ${userID} is not found`);
    // Create topup
    const topup = await TopUp.create({
        CardID: card.id,
        method: req.body.method,
        amount: req.body.amount,
        date: new Date().toLocaleDateString()
    });
    // Update card saldo
    const updatedSaldo = card.saldo + req.body.amount;
    card.set({
        saldo: updatedSaldo
    });
    await topup.save();
    await card.save();
    return res.json({ topup, card });
}

module.exports = createTopUp;