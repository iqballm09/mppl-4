const User = require("../../models/User");
const Card = require("../../models/Card");
const bcrypt = require("bcryptjs");

const updateCard = async (req, res) => {
    let hashNewPin, updatedPinNumber;
    // Get payload
    const email = req.user.email;
    const user = await User.findOne({
        where: { email: email }
    });
    if (!user) return res.status(404).send("User not found");
    // Read user card
    const card = await Card.findOne({
        where: { UserID: user.id }
    });
    // Change pin number. statusCPN = Status of Change Pin Number
    if (req.body.statusCPN === true) {
        if (req.body.oldPinNumber) {
            // Checking if old password is correct
            const validOldPinNumber = await bcrypt.compare(req.body.oldPinNumber, card.pinNumber);
            if (!validOldPinNumber) return res.status(400).send("Invalid pin number!");
            else {
                const salt = await bcrypt.genSalt(16);
                hashNewPin = await bcrypt.hash(req.body.newPinNumber, salt);
            }
        } else {
            return res.status(500).send("Old pin number is empty");
        }
    }

    // Updated pin number
    updatedPinNumber = hashNewPin;
    if (!updatedPinNumber) {
        updatedPinNumber = card.pinNumber;
    }

    // Update another fields
    card.set({
        name: req.body.name,
        pinNumber: updatedPinNumber
    });

    await card.save();

    // Get updated user
    const updatedCard = await Card.findOne({
        where: { UserID: user.id }
    });
    return res.json({ updatedCard });
}

module.exports = updateCard;