import personalDetails from 'app/entities/personal-details/personal-details.reducer';
import country from 'app/entities/country/country.reducer';
import financialDetails from 'app/entities/financial-details/financial-details.reducer';
import profession from 'app/entities/profession/profession.reducer';
import address from 'app/entities/address/address.reducer';
import currency from 'app/entities/currency/currency.reducer';
import contact from 'app/entities/contact/contact.reducer';
import questions from 'app/entities/questions/questions.reducer';
import userResponses from 'app/entities/user-responses/user-responses.reducer';
import options from 'app/entities/options/options.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  personalDetails,
  country,
  financialDetails,
  profession,
  address,
  currency,
  contact,
  questions,
  userResponses,
  options,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
