'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    return queryInterface.createTable("menus", {
      id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
      },
      name: {
        type: Sequelize.STRING(100),
        allowNull: false,
        validate: {
          notEmpty: { msg: "Menu name must have a value" }
        }
      },
      price: {
        type: Sequelize.DECIMAL(20),
        allowNull: false,
        validate: {
          notEmpty: { msg: "Menu price must have a value" }
        }
      },
      photo: {
        type: Sequelize.STRING(1024),
        defaultValue: "default-photo.png"
      },
      // Tipe menu: makanan dan minuman
      type: {
        type: Sequelize.STRING(50),
        allowNull: false,
        validate: {
          notEmpty: { msg: "Menu type must have value" },
          notNull: { msg: "Menu type must not null" }
        }
      },
      createdAt: Sequelize.DATE,
      updatedAt: Sequelize.DATE
    });
  },

  async down(queryInterface, Sequelize) {
    return queryInterface.dropTable("menus");
  }
};
