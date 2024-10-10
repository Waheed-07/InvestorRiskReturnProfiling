import verifiableAttributes from 'app/entities/SimpleRishta/verifiable-attributes/verifiable-attributes.reducer';
import userVerificationAttributes from 'app/entities/SimpleRishta/user-verification-attributes/user-verification-attributes.reducer';
import applicationConfigurations from 'app/entities/SimpleRishta/application-configurations/application-configurations.reducer';
import profileVerificationStatus from 'app/entities/SimpleRishta/profile-verification-status/profile-verification-status.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  verifiableAttributes,
  userVerificationAttributes,
  applicationConfigurations,
  profileVerificationStatus,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
