'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  up(queryInterface, Sequelize) {
    return queryInterface.addColumn(
      'cards', // name of Source model
      'UserID', // name of the key we're adding
      {
        type: Sequelize.INTEGER,
        references: {
          model: 'Users', // name of Target model
          key: 'id', // key in Target model that we're referencing
        },
        onUpdate: 'CASCADE',
        onDelete: 'SET NULL',
      }
    )
      .then(() => {
        // Payment hasMany Order
        return queryInterface.addColumn(
          'Merchants', // name of Target model
          'FoodCourtID', // name of the key we're adding
          {
            type: Sequelize.INTEGER,
            references: {
              model: 'FoodCourts', // name of Source model
              key: 'id',
            },
            onUpdate: 'CASCADE',
            onDelete: 'SET NULL',
          }
        );
      })
  },

  down: (queryInterface, Sequelize) => {
    return queryInterface.removeColumn(
      'cards', // name of Source model
      'UserID' // key we want to remove
    )
      .then(() => {
        return queryInterface.removeColumn(
          'FoodCourts', // name of the Target model
          'FoodCourtID' // key we want to remove
        );
      });
  }
};