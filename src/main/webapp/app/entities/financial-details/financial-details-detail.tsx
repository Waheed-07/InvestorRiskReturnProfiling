import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './financial-details.reducer';

export const FinancialDetailsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const financialDetailsEntity = useAppSelector(state => state.financialDetails.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="financialDetailsDetailsHeading">
          <Translate contentKey="myApp.financialDetails.detail.title">FinancialDetails</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{financialDetailsEntity.id}</dd>
          <dt>
            <span id="annualIncome">
              <Translate contentKey="myApp.financialDetails.annualIncome">Annual Income</Translate>
            </span>
          </dt>
          <dd>{financialDetailsEntity.annualIncome}</dd>
          <dt>
            <span id="netWorth">
              <Translate contentKey="myApp.financialDetails.netWorth">Net Worth</Translate>
            </span>
          </dt>
          <dd>{financialDetailsEntity.netWorth}</dd>
          <dt>
            <span id="currentSavings">
              <Translate contentKey="myApp.financialDetails.currentSavings">Current Savings</Translate>
            </span>
          </dt>
          <dd>{financialDetailsEntity.currentSavings}</dd>
          <dt>
            <span id="investmentExperience">
              <Translate contentKey="myApp.financialDetails.investmentExperience">Investment Experience</Translate>
            </span>
          </dt>
          <dd>{financialDetailsEntity.investmentExperience}</dd>
          <dt>
            <span id="sourceOfFunds">
              <Translate contentKey="myApp.financialDetails.sourceOfFunds">Source Of Funds</Translate>
            </span>
          </dt>
          <dd>{financialDetailsEntity.sourceOfFunds}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="myApp.financialDetails.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {financialDetailsEntity.createdAt ? (
              <TextFormat value={financialDetailsEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="myApp.financialDetails.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {financialDetailsEntity.updatedAt ? (
              <TextFormat value={financialDetailsEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/financial-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/financial-details/${financialDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FinancialDetailsDetail;
