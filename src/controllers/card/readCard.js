const Card = require("../../models/Card");
dotenv = require("dotenv");

dotenv.config();

// Read all users
const getAllCards = async (req, res) => {
    try {
        const cards = await Card.findAll();
        return res.status(200).json({ cards });
    } catch (error) {
        return res.status(500).send(error.message);
    }
}

// Read card by UserID
const getCardByUserID = async (req, res) => {
    // Read card by UserID
    const card = await Card.findOne({ 
        where: { UserID: req.body.UserID }
    });
    // Check if card already exists
    if (!card) return res.status(404).send("Card with specific UserID is not found");
    res.status(200).json({ card });
}

module.exports = {
    getAllCards,
    getCardByUserID
}