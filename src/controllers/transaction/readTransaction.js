const Transaction = require("../../models/Transaction");
const dotenv = require("dotenv");

dotenv.config();

const getAllTransactions = async (req, res) => {
    try {
        const transactions = await Transaction.findAll();
        return res.status(200).json({ transactions });
    } catch (error) {
        return res.status(500).send(error.message);
    }
}

const getTransaction = async (req, res) => {
    const transaction = await Transaction.findOne({
        where: {
            id: req.body.id,
            MerchantID: req.body.MerchantID,
            OrderID: req.body.OrderID,
            UserID: req.body.UserID
        }
    });
    if (!transaction) return res.status(404).send(`Transaction with ID: ${req.body.id}, MerchantID: ${req.body.MerchantID}, OrderID: ${req.body.OrderID}, and UserID: ${req.body.UserID} is not found`);
    return res.status(200).json({ transaction });
}

module.exports = {
    getAllTransactions,
    getTransaction
};